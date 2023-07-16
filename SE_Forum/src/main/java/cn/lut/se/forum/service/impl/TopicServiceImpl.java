package cn.lut.se.forum.service.impl;

import cn.lut.se.forum.dao.CategoryDao;
import cn.lut.se.forum.dao.ReplyDao;
import cn.lut.se.forum.dao.TopicDao;
import cn.lut.se.forum.domain.Category;
import cn.lut.se.forum.domain.Reply;
import cn.lut.se.forum.domain.Topic;
import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.dto.PageDTO;
import cn.lut.se.forum.service.TopicService;


import java.time.LocalDateTime;
import java.util.List;

public class TopicServiceImpl implements TopicService {


    private TopicDao topicDao = new TopicDao();

    private ReplyDao replyDao = new ReplyDao();

    private CategoryDao categoryDao = new CategoryDao();

    @Override
    /*
    根据cid查询列表
     */
    public PageDTO<Topic> listTopicPageByCid(int cId, int page, int pageSize) {

        //查询总记录数
        int totalRecordNum = topicDao.countTotalTopicByCid(cId);

        int from = (page-1) * pageSize;

        //分页查询
        List<Topic> topicList =  topicDao.findListByCid(cId,from,pageSize);

        PageDTO<Topic> pageDTO = new PageDTO<>(page,pageSize,totalRecordNum);

        pageDTO.setList(topicList);

        return pageDTO;
    }


    @Override
    public Topic findById(int topicId) {

        return topicDao.findById(topicId);
    }



    /*
    根据username查询列表
     */
    public PageDTO<Topic> listTopicPageByUsername(String username, int page, int pageSize) {
        //查询总记录数
        int totalRecordNum = topicDao.countTotalTopicByUsername(username);

        int from = (page-1) * pageSize;

        //分用户查询
        List<Topic> topicList =  topicDao.findListByUsername(username,from,pageSize);

        PageDTO<Topic> pageDTO = new PageDTO<>(page,pageSize,totalRecordNum);

        pageDTO.setList(topicList);

        return pageDTO;
    }



    public Topic findByUsername(String username) {

        return topicDao.findByUsername(username);
    }


    @Override
    public void DeleteReplyByUsernameAndContentAndAuthor( String username,String content,String author){

       int i = replyDao.Delete(username,content,author);
       if( i == 0 ) {
           System.out.println("删除失败！！！");
       }else
           System.out.println("删除成功！！！");
    }










    @Override
    public PageDTO<Reply> findReplyPageByTopicId(int topicId, int page, int pageSize) {

        //查询总的回复
        int totalRecordNum = replyDao.countTotalReplyByCid(topicId);

        int from = (page-1) * pageSize;

        //分页查询
        List<Reply> replyList =  replyDao.findListByTopicId(topicId,from,pageSize);

        PageDTO<Reply> pageDTO = new PageDTO<>(page,pageSize,totalRecordNum);

        pageDTO.setList(replyList);

        return pageDTO;

    }


    public PageDTO<Reply> findReplyPageByUsername(String username, int page, int pageSize) {

        //查询总的回复
        int totalRecordNum = replyDao.countTotalReplyByUsername(username);

        int from = (page-1) * pageSize;

        //分页查询
        List<Reply> replyList =  replyDao.findListByUsername(username,from,pageSize);

        PageDTO<Reply> pageDTO = new PageDTO<>(page,pageSize,totalRecordNum);

        pageDTO.setList(replyList);

        return pageDTO;

    }








    @Override
    public int addTopic(User loginUser, String title, String content, int cId) {

        Category category = categoryDao.findById(cId);
        if(category == null){ return 0;}

        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setContent(content);
        topic.setCreateTime(LocalDateTime.now());
        topic.setUpdateTime(LocalDateTime.now());
        topic.setPv(1);
        topic.setDelete(0);
        topic.setUserId(loginUser.getId());
        topic.setUsername(loginUser.getUsername());
        topic.setUserImg(loginUser.getImg());
        topic.setcId(cId);
        topic.setHot(0);

        int rows = 0;
        try {
            rows = topicDao.save(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows;
    }

    @Override
    public int replyByTopicId(User loginUser, int topicId, String content, String author) {

        int floor = topicDao.findLatestFloorByTopicId(topicId);

        Reply reply = new Reply();
        reply.setContent(content);
        reply.setCreateTime(LocalDateTime.now());
        reply.setUpdateTime(LocalDateTime.now());
        reply.setFloor(floor+1);
        reply.setTopicId(topicId);
        reply.setUserId(loginUser.getId());
        reply.setUsername(loginUser.getUsername());
        reply.setUserImg(loginUser.getImg());
        reply.setDelete(0);
        reply.setAuthor(author);


        int rows = replyDao.save(reply);

        return rows;
    }

    @Override
    public void addOnePV(int topicId) {

        Topic topic = topicDao.findById(topicId);

        int newPV = topic.getPv()+1;

        topicDao.updatePV(topicId,newPV,topic.getPv());

    }
}

