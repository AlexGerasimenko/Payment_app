package com.my.data_base;

import com.my.exception.Messages;

import org.apache.log4j.Logger;

import javax.naming.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtils {

    private static final Logger LOG = Logger.getLogger(DBUtils.class);

    private static DBUtils instance;

    public static synchronized DBUtils getInstance() {
        if (instance == null) {
            instance = new DBUtils();
        }
        return instance;
    }

    private DataSource ds;

    private DBUtils() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/db_pay");
        } catch (NamingException ex) {
            throw new IllegalStateException("Cannot obtain a data source", ex);
        }
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    /**
     * Closes a connection.
     *
     * @param con Connection to be closed.
     */
    public static void close(Connection con, String userLocale) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes a statement object.
     */
    public static void close(Statement stmt, String userLocale) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes a result set object.
     */
    public static void close(ResultSet rs, String userLocale) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULT_SET, ex);
            }
        }
    }

    /**
     * Closes resources.
     */
    public static void close(Connection con, Statement stmt, String userLocale) {
        close(stmt, userLocale);
        close(con, userLocale);
    }


    /**
     * Closes resources.
     */
    public static void close(Connection con, Statement stmt, ResultSet rs, String userLocale) {
        close(rs, userLocale);
        close(stmt, userLocale);
        close(con, userLocale);
    }

    /**
     * Rollbacks a connection.
     *
     * @param con Connection to be rollbacked.
     */
    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }


}
