package lesson01;

import java.sql.*;

/**
 * 发现jdbc的编程问题
 * 先写一个标准的jdbc
 * 看看代码有多他妈的长吧
 *
 * @author huoguangyao
 */
public class HelloJDBC {
    public static void main(String[] args) {

        /*
        代码太多了。对于上班的工作开发
        处理太多的异常

        并且从resultSet拿到结果后，没有映射成java bean。不过这得看需求是否需要
         */
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        // 得注册驱动
        // 通过driverManager获取链接
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306xxxxxxx");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user");

            while (resultSet.next()) {
                // 这是按列的 index开始取， 开始是0还是1 看源码学习。
                resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
