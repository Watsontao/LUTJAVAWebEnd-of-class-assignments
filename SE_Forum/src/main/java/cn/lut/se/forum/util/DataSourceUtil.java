package cn.lut.se.forum.util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * 数据库连接池工具类
 *  这个工具类的目的：
 *   获取druid的数据源对象DataSource ** 工具类的封装原则：
 *   1、方法通常都是类方法！
 *   2、方法内部如果产生了异常，采用try catch方式来处理
 */


public class DataSourceUtil {

    //先声明数据源
    private static DataSource dataSource;

    /*
    静态代码块，类加载的时候就初始化了。  也就是说，在项目一运行 加载类的时候数据库就已经建立连接了 而且只执行一次 。
     */
    static {

        try{

            //创建输入流对象，去读取druid.properties  --vincent
            InputStream in = DataSourceUtil.class.getClassLoader().getResourceAsStream("database.properties");

            //创建Properties属性文件对象  --vincent
            Properties p = new Properties();

            // 将输入流读取到的文件内容加载到属性文件对象中！  --vincent
            p.load(in);

            //将属性文件对象传入到Druid创建数据源的方法中，就可以按照我们配置文件中的信息，初 始化数据源了！  --vincent
            dataSource = BasicDataSourceFactory.createDataSource(p);

            System.out.println("数据库连接成功");

        }catch (Exception e){
            e.printStackTrace();
            throw new ExceptionInInitializerError("初始化DBPC失败");
        }
    }

    /**
     * 获取连接池
     * @return DataSource
     */
    public static DataSource getDataSource(){
        return dataSource;
    }



}
