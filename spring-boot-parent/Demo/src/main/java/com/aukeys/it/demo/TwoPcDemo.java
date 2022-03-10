package com.aukeys.it.demo;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TwoPcDemo {

    public static AtomikosDataSourceBean createAtomikosDataSourceBean(String dbName) {
        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:mysql://localhost:3306/" + dbName);
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        //atomikos相当于中间者，是代理数据源
        //将AtomikosDataSourceBean封装成MysqlXADataSource
        AtomikosDataSourceBean sourceBean = new AtomikosDataSourceBean();
        sourceBean.setUniqueResourceName(dbName);
        sourceBean.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        sourceBean.setXaProperties(properties);
        return sourceBean;
    }

    public static void main(String[] args) {
        //分别获取两个数据源
        AtomikosDataSourceBean ds1 = createAtomikosDataSourceBean("db_user");//数据库名
        AtomikosDataSourceBean ds2 = createAtomikosDataSourceBean("db_user");//数据库名
        //直接拿到两个数据源的两个连接
        Connection conn1 = null;
        Connection conn2 = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        //设置全局事务
        UserTransaction transaction = new UserTransactionImp();
        try {
            //开启事务
            transaction.begin();
            //执行db1的sql
            conn1 = ds1.getConnection();
            ps1 = conn1.prepareStatement("insert into user(name) values (?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, "zhangsan");
            //模拟异常
            int i = 1 / 0;
            //执行db2的sql
            conn2 = ds2.getConnection();
            ps2 = conn2.prepareStatement("insert into account(name) values (?)");
            ps2.setString(1, "100");
            //两阶段提交,重点在这！！
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                transaction.rollback();//回滚
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                ps1.close();ps2.close();conn1.close();conn2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
