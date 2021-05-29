package com.zqx.dao;

import com.zqx.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public abstract class BaseDao {

    QueryRunner queryRunner = new QueryRunner();

    /**
     *功能描述 对数据库的更新操作，如增删改
     * @author zqx
     * @date 2021/5/28
      * @param sql
     * @param args
     * @return int 返回影响的行数，失败返回0
     */
    public int update(String sql, Object...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *功能描述 查询一条数据
     * @author zqx
     * @date 2021/5/28
      * @param type
     * @param sql
     * @param args
     * @return T 返回一个指点的对象
     */
    public <T> T queryForOne(Class<T> type, String sql, Object...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql, new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *功能描述 查询多条记录
     * @author zqx
     * @date 2021/5/28
      * @param type
     * @param sql
     * @param args
     * @return java.util.List<T> 返回一个指定对象的集合
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     *功能描述 查询一些特殊值，如平均数或总数
     * @author zqx
     * @date 2021/5/28
      * @param sql
     * @param args
     * @return java.lang.Object
     */
    public Object queryForSingleValue(String sql,Object...args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
