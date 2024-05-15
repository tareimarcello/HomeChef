package progettoispw.letmeknow.controller;

import progettoispw.letmeknow.Exceptions;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class ConnectionDBMS {
    private static String  user ;
    private static String password;
    private static String dburl ;
    private static String driverclassname ;
    private static java.sql.Connection conn;
    private static int numConnection;
    public static void setValues(){
        ConnectionInfo connectionInfo = new ConnectionInfo();
        Map<String, String> parameters = connectionInfo.getConnectionInfo();
        user=parameters.get("username");
        password=parameters.get("password");
        dburl=parameters.get("url");
        driverclassname=parameters.get("driverName");
    }
    public ConnectionDBMS()  {
       try {
           if (conn == null || conn.isClosed())  getConn();
       }catch(SQLException throwables){
           closeConn();
           Exceptions.exceptionConnectionOccurred();
       }
    }

    private static void getConn(){
        try {
            setValues();
            Class.forName(driverclassname);//recupera dinamicamente il driver , prende la classe dal class path
            conn = DriverManager.getConnection(dburl, user ,password);//quando ho get getSTMT ho il driver caricato
        } catch (Exception e) {
            e.printStackTrace();
            Exceptions.exceptionConnectionOccurred();
        }
    }
    public Statement getSTMT(Statement stmt){
        if(numConnection<1) {
            try {
                if(conn==null)getConn();
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                increm();
                return stmt;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                closeSTMT(stmt);
                Exceptions.exceptionConnectionOccurred();
            }
        }
        return null;
    }
    private static void decrem(){
        numConnection--;
    }
    private static void increm(){
        numConnection++;
    }
    public void closeSTMT(Statement stmt) {
        try {
            if(stmt!=null)stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Exceptions.exceptionConnectionOccurred();
        } finally {
            decrem();
        }
    }
    public void closeRSTSTMT(ResultSet rst, Statement stmt) {
        try {
            if(rst!=null)rst.close();
            closeSTMT(stmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Exceptions.exceptionConnectionOccurred();
        } finally {
            decrem();
        }
    }
    public static void closeConn(){
        try {
            if(conn!=null)conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Exceptions.exceptionConnectionOccurred();
        } finally {
            decrem();
        }
    }
}