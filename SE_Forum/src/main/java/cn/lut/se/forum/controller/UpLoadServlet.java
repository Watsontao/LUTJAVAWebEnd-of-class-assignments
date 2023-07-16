package cn.lut.se.forum.controller;

/**
 * @author vincent
 * @create 2022-11-06 19:43
 */


import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/upload")
public class UpLoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * upload Img
         * 上传头像
         * */
        PrintWriter out = response.getWriter();

        DiskFileItemFactory factory = new DiskFileItemFactory();

        factory.setSizeThreshold(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD);//上传文件大小设置 上限10M

        File _file = new File(getServletContext().getRealPath("/temp"));
        factory.setRepository(_file);//临时文件路径
        if(!_file.exists()){
            System.out.println(_file.mkdir());
        }

        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        servletFileUpload.setFileSizeMax(10*1024*1024);//文件大小上限
        servletFileUpload.setHeaderEncoding("utf-8");//设置响应头

        String name = "";
        if(!ServletFileUpload.isMultipartContent(request)){
            return;
        }
        List<FileItem> list = null ;
        try {
            list = servletFileUpload.parseRequest(request);
            if(list == null){ //list为空 结束
                return;
            }
            for(FileItem fileItem:list){
                if(fileItem.isFormField()){
                    String value = fileItem.getString("utf-8");
                }else {
//                    fileItem.setFieldName();
                    name = fileItem.getName();//文件名称

                    File dir  = new File("/Users/vincent/IdeaProjects/SE_Forum/src/main/webapp/static/pic");//文件下载路径
                    if(!dir.exists()){//如果文件夹不存在 创建一个文件夹

                        dir.mkdir();
                    }
                    fileItem.write(new File(dir+File.separator+name));


//
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
                对上传的文件进行重命名
         */
        File file  = new File("/Users/vincent/IdeaProjects/SE_Forum/src/main/webapp/static/pic/"+name);// 上传后  改名前
        User user = (User) request.getSession(true ).getAttribute("loginUser");//获取到了已登录的user对象
        String new_name = "static//pic//"+user.getPhone()+".jpg";
        File new_file = new File("/Users/vincent/IdeaProjects/SE_Forum/src/main/webapp/"+new_name);//改名后

        if (new_file.exists()) {  //  确保新的文件名不存在
            System.out.println("文件存在，删除状态:"+new_file.delete());
        }
        if(file.renameTo(new_file)) {
            System.out.println("已重命名");
        } else {
            System.out.println("更新图片");
        }

        /*
         * update Img
         * 更新用户头像
         * */

        new_name = "static//pic//"+user.getPhone()+".jpg";

        user.setImg(new_name);
        QueryRunner qr = new QueryRunner( new DataSourceUtil().getDataSource());
        String sql = "update user set img = ? where phone = ?";


        try {
            int n = qr.update(sql,user.getImg(),user.getPhone());
            if(n>0){
                request.setAttribute("loginUser",user);
            }else {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/success.jsp").forward(request,response);//跳转到头像上传成功页面

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


}
