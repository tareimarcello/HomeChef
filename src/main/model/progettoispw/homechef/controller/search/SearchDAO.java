package progettoispw.letmeknow.controller.search;

import progettoispw.letmeknow.controller.ConnectionDBMS;
import progettoispw.letmeknow.controller.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO implements Dao {
    ConnectionDBMS connDB;
    Query query;
    public static final String UID ="userid";
    public SearchDAO() {
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
    public void attach(String input, List<String> list) {
        boolean bool = true;
        for (String elem : list) {
            if (elem.equals(input)) bool = false;
        }
        if (bool) list.add(input);
    }
    public List<String> paramSearch(String uid, int emp, int hum, int pos) {
        Statement stmt = null;
        ResultSet rst = null;
        List<String> ret = new ArrayList<>();
        try {
            stmt = connDB.getSTMT(stmt);
            rst = query.searchAll(stmt, uid, emp, hum, pos);
            while (rst.next()) {
                attach(rst.getString(UID), ret);
            }
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            connDB.closeRSTSTMT(rst, stmt);
        }
    }
    public void attach(String input,String check,List<String> list){
            if (input!=null && !list.contains(input) && !input.equals(check)) list.add(input);
    }
    public boolean addVisited(String userid, String userid2) {
        Statement stmt = null;
        ResultSet rst = null;
        boolean bool = false;
        List<String>prev=new ArrayList<>();
        String [] toQuery=new String[4];
        try {
            stmt = connDB.getSTMT(stmt);
            rst = query.getVisited(stmt, userid);
            prev.add(userid2);
            if (rst.next()) {
                bool=true;
                attach(rst.getString(1),userid2,prev);
                attach(rst.getString(2),userid2,prev);
                attach(rst.getString(3),userid2,prev);
            }
            if(!bool){
                bool=query.newLine(stmt,userid);
            }
            int index=0;
            for(String str:prev)toQuery[index++]=str;
            if(bool)bool=query.setVisited(stmt, userid,toQuery);
            if(bool)bool=query.incremVisit(stmt,userid2);
            return bool;
        } catch (SQLException throwables) {
            return false;
        } finally {
            connDB.closeRSTSTMT(rst, stmt);
        }
    }
    public int[] getnVisit(String userid) {
        Statement stmt = null;
        ResultSet rst = null;
        boolean bool=true;
        int [] ret = new int [2];
        try {
            stmt = connDB.getSTMT(stmt);
            query.newLine(stmt,userid);
            
            rst = query.getnVisit(stmt, userid);
            if (rst.next()) {
                ret[0]=Integer.parseInt(rst.getString(5));
                bool=false;
            }
            if(bool){
                bool=query.newLine(stmt,userid);
                if(bool)ret[0]= Integer.parseInt(rst.getString(1));
            }
            rst = query.getnRows(stmt);
            if (rst.next()) ret [1]=Integer.parseInt(rst.getString(1));
            return ret;
        } catch (Exception throwables) {
            return new int [2];
        } finally {
            connDB.closeRSTSTMT(rst, stmt);
        }
    }
    public List<String> getVisit(String userid) {
        Statement stmt = null;
        ResultSet rst = null;
        ArrayList<String> users = new ArrayList<>();
        try {
            stmt = connDB.getSTMT(stmt);
            rst = query.getVisited(stmt, userid);
            while (rst.next()) {
                users.add(rst.getString (1));
                users.add(rst.getString (2));
                users.add(rst.getString (3));
            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        } finally {
            connDB.closeRSTSTMT(rst, stmt);
        }
    }

}