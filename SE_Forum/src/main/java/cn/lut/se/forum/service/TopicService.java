package cn.lut.se.forum.service;

import cn.lut.se.forum.domain.Reply;
import cn.lut.se.forum.domain.Topic;
import cn.lut.se.forum.domain.User;
import cn.lut.se.forum.dto.PageDTO;

public interface TopicService  {

    PageDTO<Topic> listTopicPageByCid(int cId, int page, int pageSize);
    PageDTO<Topic> listTopicPageByUsername(String username, int page, int pageSize);

    Topic findById(int topicId);

    PageDTO<Reply> findReplyPageByTopicId(int topicId, int page, int pageSize);

    int addTopic(User loginUser, String title, String content, int cId);

    int replyByTopicId(User loginUser, int topicId, String content,String author);

    public PageDTO<Reply> findReplyPageByUsername(String username, int page, int pageSize);

    public void DeleteReplyByUsernameAndContentAndAuthor( String username,String content,String author);

    void addOnePV(int topicId);
}