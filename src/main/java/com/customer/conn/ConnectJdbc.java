package com.customer.conn;

import com.customer.common.Comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJdbc {
    public static Connection con (){
        try {
            Connection connection = DriverManager.getConnection(Comm.MY_SQL_URL,Comm.USERNAME_MYSQL,Comm.PASSWORD_MYSQL);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
