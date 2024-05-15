package progettoispw.letmeknow.controller.usruser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    protected ResultSet selectUser(Statement stmt, String iduser )throws SQLException {
        String sql=String.format(" SELECT * FROM utenti  where userid = '%s' ;",iduser);
        return stmt.executeQuery(sql);
    }
    protected boolean setDB(Statement stmt, String iduser ,String what,String edit){
        try {
            String sql=String.format(" UPDATE  `utenti` set `%s`='%s' WHERE (`userid` = '%s') ;",what,edit,iduser);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    protected boolean setParams(Statement stmt, String uid, int[] param){
        try {
            String sql=String.format("UPDATE `utenti` SET `empathy` = '%d', `humor` = '%d', `positivity` = '%d' WHERE (`userid` = '%s'); ",param[0],param[1],param[2],uid);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }
    protected ResultSet queryResult(Statement stmt, String uid) {
        try {
            String sql=String.format(" SELECT * FROM forms where `userid`=%s ;",uid);
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    protected boolean setCalculated(Statement stmt,String uid,int formid){
        try {
            String sql=String.format(" UPDATE `forms` SET `calculated` = '0' WHERE (`formid` = '%d') and (`userid` = '%s');",formid,uid);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }return false;
    }
}
