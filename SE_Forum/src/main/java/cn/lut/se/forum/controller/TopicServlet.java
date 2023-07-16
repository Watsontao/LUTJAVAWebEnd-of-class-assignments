package cn.lut.se.forum.controller;

import cn.lut.se.forum.domain.Reply;
import cn.lut.se.forum.domain.Topic;
import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.dto.PageDTO;
import cn.lut.se.forum.service.CategoryService;
import cn.lut.se.forum.service.TopicService;
import cn.lut.se.forum.service.impl.CategoryServiceImpl;
import cn.lut.se.forum.service.impl.TopicServiceImpl;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "topicServle", urlPatterns = "/topic")
public class TopicServlet extends BaseServlet{
    private TopicService topicService = new TopicServiceImpl();

    private CategoryService categoryService = new CategoryServiceImpl();
    /**
     * 默认分页大小
     */
    //总共5页
    private static final int pageSize = 5;


    /**
     * http://localhost:8080/topic?method=list&c_id=2&page=2
     *
     * topic分页接口
     * @param request
     * @param response
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int cId = Integer.parseInt(request.getParameter("c_id"));

        //默认第一页
        int page=1;

        //当前页数
        String currentPage = request.getParameter("page");
//        System.out.println("当前是第"+currentPage);

        if(currentPage !=null && currentPage !=""){
            page = Integer.parseInt(currentPage);
            if(page < 0){
                page = 1 ;
            }
        }

        PageDTO<Topic> pageDTO =  topicService.listTopicPageByCid(cId,page,pageSize);

        //种类  --vincent
        request.getSession().setAttribute("categoryList",categoryService.list());

        //分页设计  --vincent
        request.setAttribute("topicPage" ,pageDTO);

        //转发给index.jsp  --vincent
        request.getRequestDispatcher("/index.jsp").forward(request,response);

    }




















    public void list_2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int cId = Integer.parseInt(request.getParameter("c_id"));

        //默认第一页
        int page=1;

        //当前页数
        String currentPage = request.getParameter("page");
//        System.out.println("当前是第"+currentPage);

        if(currentPage !=null && currentPage !=""){
            page = Integer.parseInt(currentPage);
            if(page < 0){
                page = 1 ;
            }
        }

        /*通过username查帖子*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");

        //通过名字查询帖子
        PageDTO<Topic> pageDTO_2 =  topicService.listTopicPageByUsername(user.getUsername(),page,pageSize);

         //种类  --vincent
        request.getSession().setAttribute("categoryList",categoryService.list());

        //分页设计  --vincent
        request.setAttribute("topicPage2" ,pageDTO_2);


        //获取用户的全部回复
        PageDTO<Reply> pageDTO =  topicService.findReplyPageByUsername(user.getUsername(), page,pageSize);




        request.setAttribute("replyPage_2",pageDTO);

        //转发给mySpace.jsp  --vincent
        request.getRequestDispatcher("/mySpace.jsp").forward(request,response);

    }





    /**
     * http://localhost:8080/topic?method=findDetailById&topic_id=1&page=1
     *
     * 查看主题的全部回复
     * @param request
     * @param response
     */
    public void  findDetailById(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        //获取topicid
        int topicId = Integer.parseInt(request.getParameter("topic_id"));
        HttpSession session = request.getSession();
        session.setAttribute("topicId",topicId);

        //默认第一页
        int page=1;

        String currentPage = request.getParameter("page");

        if(currentPage !=null && currentPage !=""){
            page = Integer.parseInt(currentPage);
            if(page < 0){
                page = 1 ;
            }
        }

        //处理浏览量，如果同个session内只算一次
        String sessionReadKey = "is_read_"+topicId;

        Boolean isRead = (Boolean) request.getSession().getAttribute(sessionReadKey);

        if(isRead == null){
            request.getSession().setAttribute(sessionReadKey,true);
            //新增一个pv
            topicService.addOnePV(topicId);

        }

        Topic topic = topicService.findById(topicId);

        PageDTO<Reply> pageDTO =  topicService.findReplyPageByTopicId(topicId,page,pageSize);

//        System.out.println(pageDTO.toString());

        request.setAttribute("topic" ,topic);

        request.setAttribute("replyPage",pageDTO);

        request.getRequestDispatcher("/topic_detail.jsp").forward(request,response);

    }



    //通过用户名，内容 和 帖子对作者删除回复
    public void DeleteReplyByUsernameAndContentAndAuthor(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException  {

        String username =request.getParameter("username");
        String content =request.getParameter("content");
        String author =request.getParameter("author");
        int topicId = Integer.parseInt(request.getParameter("topic_id"));
        System.out.println(topicId);
        System.out.println("loginUser：" +username);
        System.out.println("reply'scontent："+content);
        System.out.println("toptic's author："+author);


        topicService.DeleteReplyByUsernameAndContentAndAuthor(username,content,author);


        request.getRequestDispatcher("/topic?method=findDetailById&topic_id="+topicId).forward(request,response);



    }

    /**
     * http://localhost:8080/topic?method=addTopic
     * 发布主题
     * @param request
     * @param response
     */
    public void addTopic(HttpServletRequest request,HttpServletResponse response) throws IOException {

        //先判断用户有没有登录
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if(loginUser == null){
            request.setAttribute("msg","请登录");
            response.sendRedirect(request.getContextPath()+"/user/login.jsp");
            return;
            //页面跳转 TODO
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int cId = Integer.parseInt(request.getParameter("c_id"));

        topicService.addTopic(loginUser,title,content,cId);

        //发布主题成功
        response.sendRedirect(request.getContextPath()+"/topic?method=list&c_id="+cId);
    }

    /**
     * http://localhost:8080/topic?method=replyByTopicId&topic_id=9
     * 盖楼回复
     * @param request
     * @param response
     */
    public void replyByTopicId(HttpServletRequest request,HttpServletResponse response) throws IOException {

        //先判断用户有没有登录
        User loginUser = (User)request.getSession().getAttribute("loginUser");
        if(loginUser == null){
            request.setAttribute("msg","请登录");
            response.sendRedirect("/user/login.jsp");
            return;

        }

        int topicId = Integer.parseInt(request.getParameter("topic_id"));

        String content = request.getParameter("content");
        String author = request.getParameter("author");

        System.out.println("测试author："+author);

        topicService.replyByTopicId(loginUser,topicId,content,author);

        response.sendRedirect(request.getContextPath()+"/topic?method=findDetailById&topic_id="+topicId+"&page=1");

    }

}
