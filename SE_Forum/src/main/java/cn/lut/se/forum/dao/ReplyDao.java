package cn.lut.se.forum.dao;

import cn.lut.se.forum.domain.Reply;
import cn.lut.se.forum.util.DataSourceUtil;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ReplyDao {


    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());

    //开启驼峰映射
    private BeanProcessor beanProcessor = new GenerousBeanProcessor();
    private RowProcessor processor = new BasicRowProcessor(beanProcessor);



    /**
     * 根据topic_id查询回复总记录
     * @param topicId
     * @return
     */
    public int countTotalReplyByCid(int topicId) {

        String sql = "select count(*) from reply where topic_id=?";

        Long count = null;
        try {
            count =  (Long)queryRunner.query(sql,new ScalarHandler<>(),topicId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count.intValue();
    }


    public List<Reply> findListByTopicId(int topicId, int from, int pageSize) {

        String sql = "select * from reply where topic_id=? order by create_time asc limit ?,?";

        List<Reply> replyList = null;

        try{

            replyList =  queryRunner.query(sql,new BeanListHandler<>(Reply.class,processor),topicId,from,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }

        return replyList;
    }





    /**
     * 根据username查询回复总记录
     * @param username
     * @return
     */
    public int countTotalReplyByUsername(String username) {

        String sql = "select count(*) from reply where username=?";

        Long count = null;
        try {
            count =  (Long)queryRunner.query(sql,new ScalarHandler<>(),username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count.intValue();
    }


    public List<Reply> findListByUsername(String username, int from, int pageSize) {

        String sql = "select * from reply where username=? order by create_time asc limit ?,?";

        List<Reply> replyList = null;

        try{

            replyList =  queryRunner.query(sql,new BeanListHandler<>(Reply.class,processor),username,from,pageSize);
        }catch (Exception e){
            e.printStackTrace();
        }

        return replyList;
    }


    public int save(Reply reply) {

        String sql = "insert into reply (topic_id,floor,content,user_id,username,user_img,create_time,update_time,`delete`,author)" +
                "values (?,?,?,?,?,?,?,?,?,?)";

        Object [] params = {
                reply.getTopicId(),
                reply.getFloor(),
                reply.getContent(),
                reply.getUserId(),
                reply.getUsername(),
                reply.getUserImg(),
                reply.getCreateTime(),
                reply.getUpdateTime(),
                reply.getDelete(),
                reply.getAuthor()
        };

        int rows = 0;
        try{

            rows = queryRunner.update(sql,params);

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows;
    }





    public int Delete(String username,String content,String author) {

        String sql = "DELETE FROM reply  where username=? and content=? and author=?";
        int rows = 0;
        try{

            rows = queryRunner.update(sql,username,content,author);

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows;
    }
}
