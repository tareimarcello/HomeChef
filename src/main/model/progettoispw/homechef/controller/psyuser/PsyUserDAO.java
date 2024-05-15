package progettoispw.letmeknow.controller.psyuser;

import progettoispw.letmeknow.controller.ConnectionDBMS;
import progettoispw.letmeknow.controller.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PsyUserDAO implements Dao {
    ConnectionDBMS connDB;
    Query query;
    public static final String UID ="userid";
    public static final int FORMID=1;
    public static final int START=3;//dove iniziano le risposte
    public PsyUserDAO() {
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
    public List<Form> collectForms(int month, int year) {
        Statement stmt=null;
        ResultSet rst=null;
        Form form=new Form();
        try {
            stmt=connDB.getSTMT(stmt);
            rst=query.selectResult(stmt,month,year);
            int [] answers;
            List<Form> list=new ArrayList<>();
            while(rst.next()) {
                answers = new int[6];
                for (int i = 0; i < 6; i++) {
                    answers[i] = Integer.parseInt(rst.getString(START + i));//3=inizio dei valori delle risposte
                }
                list=  form.attach(Integer.parseInt(rst.getString(FORMID)), answers,rst.getString(UID), list);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }finally{
            connDB.closeRSTSTMT(rst,stmt);
        }
    }
    public boolean suggestForm(String from,String what){
        Statement stmt=null;
        try{
            stmt=connDB.getSTMT(stmt);
            return query.suggestForm(stmt,from,what);
        } finally {
            connDB.closeSTMT(stmt);
        }
    }
}