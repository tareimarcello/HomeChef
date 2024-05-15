package progettoispw.letmeknow.controller.chat;

import progettoispw.letmeknow.controller.ConnectionDBMS;
import progettoispw.letmeknow.controller.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO implements Dao {
    ConnectionDBMS connDB;
    Query query;
    public static final String TEXT="text";
    public static final String SENDER="sender";
    public static final String RECIVER="reciver";
    public static final String DATETIME="datetime";
    public MessageDAO() {
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
    private void scanner(ResultSet rst, Statement stmt, ArrayList<Message>list) {
        try{
            while(rst.next()){
                Message message=new Message();
                message.setText(rst.getString(TEXT));
                message.setSender(rst.getString(SENDER));
                message.setReciver(rst.getString(RECIVER));
                message.setDate(rst.getString(DATETIME));
                list.add(message);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            connDB.closeRSTSTMT(rst,stmt);
        }
    }
    public List<Message> getRecivedSentMessage(String userid) {
        Statement stmt = null;
        ResultSet rst;
        stmt= connDB.getSTMT(stmt);
        ArrayList<Message> ret = new ArrayList<>();
        rst = query.selectUserRSALL(stmt, userid);
        scanner(rst,stmt,ret);
        return ret;
    }
    public List<Message> getRecivedSentMessage(String userid1,String userid2){
        Statement stmt = null;
        ResultSet rst;
        stmt= connDB.getSTMT(stmt);
        ArrayList<Message> ret = new ArrayList<>();
        rst = query.selectUserRS(stmt, userid1,userid2);
        scanner(rst,stmt,ret);
        return ret;
    }
    public boolean newMessage(String from,String to,String text) {
        Statement stmt=null;
        try {
            stmt=connDB.getSTMT(stmt);
            String replace= "\\"+"\"";
            text=text.replace("\"",replace);
            return query.newMSG(stmt,from,to,text);
        } finally{
            connDB.closeSTMT(stmt);
        }
    }



}