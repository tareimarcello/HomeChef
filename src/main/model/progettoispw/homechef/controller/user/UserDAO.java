package progettoispw.letmeknow.controller.user;

import progettoispw.letmeknow.controller.ConnectionDBMS;
import progettoispw.letmeknow.controller.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Dao {
    ConnectionDBMS connDB;
    Query query;
    public static final String UID ="userid";
    public static final String TYPE="type";
    public static final String PASSWORD="password";
    public static final String EMAIL="email";
    public UserDAO() {
        getConn();
        getQuery();
    }
    @Override
    public void getConn() {
        connDB=new ConnectionDBMS();
    }
    @Override
    public void getQuery() {
        query=new Query();
    }
    public String [] selectUser(String uid){
        Statement stmt=null;
        ResultSet rst=null;
        String [] ret=new String [4];
        try {
            stmt=connDB.getSTMT(stmt);
            rst=query.selectUser(stmt,uid);
            while(rst.next()) {
                ret[0]=rst.getString(UID);
                ret[1]=rst.getString(PASSWORD);
                ret[2]=rst.getString(TYPE);
                ret[3]=rst.getString(EMAIL);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[4];
        }finally{
            connDB.closeRSTSTMT(rst,stmt);
        }
    }
    public String getPswd(String uid){
        Statement stmt=null;
        ResultSet rst=null;
        String ret=null;
        try {
            stmt=connDB.getSTMT(stmt);
            rst=query.selectUser(stmt,uid);
            if(rst.next()) {
                ret=rst.getString(PASSWORD);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally{
            connDB.closeRSTSTMT(rst,stmt);
        }
    }
    public boolean setPswd(String userid,String input){
        Statement stmt=null;
        try {
            stmt=connDB.getSTMT(stmt);
            return query.setDB(stmt,userid,PASSWORD,input);
        }finally{
            connDB.closeSTMT(stmt);
        }

    }
    public boolean setEmail(String userid,String input){
        Statement stmt=null;
        try {
            stmt=connDB.getSTMT(stmt);
            return query.setDB(stmt,userid,EMAIL,input);
        }finally{
            connDB.closeSTMT(stmt);
        }
    }
    public boolean checkMail(String input){
        Statement stmt=null;
        ResultSet rst=null;
        try {
            stmt=connDB.getSTMT(stmt);
            rst= query.selectUser(stmt,EMAIL,input);
            return rst.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally{
            connDB.closeRSTSTMT(rst,stmt);
        }
    }
    public boolean registration(String uid,String password, String type, String email) {
        Statement stmt=null;
        try {
            stmt=connDB.getSTMT(stmt);
            return query.newLine(stmt,uid,password,type,email);
        } finally{
            connDB.closeRSTSTMT(null,stmt);
        }
    }
    public boolean registration(String uid,String password, String type ,int[] val,String description,String email,String goal ){
        Statement stmt=null;
        try {
            String []log=new String[4];
            log[0]=uid;
            log[1]=password;
            log[2]=type;
            log[3]=email;
            stmt=connDB.getSTMT(stmt);
            return  query.newLine(stmt,log,val,description,goal);
        } finally{
            connDB.closeSTMT(stmt);
        }
    }
    public boolean feed(String userid,String input){
            Statement stmt=null;
            try {
                stmt=connDB.getSTMT(stmt);
                return query.feed(stmt,userid,input);
            } finally{
                connDB.closeSTMT(stmt);
            }

        }
    public List<String> getUID() {
        Statement stmt=null;
        ResultSet rst=null;
        try {
            stmt=connDB.getSTMT(stmt);
            ArrayList<String> uid = new ArrayList<>();
            rst = query.queryUid(stmt);
            while (rst.next()) {
                uid.add(rst.getString(UID));
            }
            return uid;
        } catch (SQLException e) {
            return new ArrayList<>();
        }finally{
            connDB.closeRSTSTMT(rst,stmt);
        }
    }
    public String[] recover (String email){
        Statement stmt=null;
        ResultSet rst=null;
        String [] ret=new String[2];
        try{
            stmt=connDB.getSTMT(stmt);
            rst=query.selectUser(stmt,EMAIL,email);
            while(rst.next()){
                ret [0]=rst.getString(UID);
                ret [1]=rst.getString(PASSWORD);
                break;
            }
            return ret;
        } catch (Exception throwables) {
            return new String[2];
        }finally{
            connDB.closeSTMT(stmt);
        }
    }
}
