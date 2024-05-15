package progettoispw.letmeknow.controller.chat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    protected ResultSet selectUserRSALL(Statement stmt, String iduser ){
        try {
            String sql=String.format(" SELECT *  FROM messages where reciver = '%s' or sender = '%s' ",iduser,iduser);
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    protected ResultSet selectUserRS(Statement stmt, String user1 , String user2) {
        try {
            String sql=String.format(" SELECT *" +" FROM messages " +
                    "where (reciver = '%s' and sender = '%s') or " +
                    "(reciver = '%s' and sender = '%s');",user1,user2,user2,user1);
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    protected boolean newMSG(Statement stmt, String from , String who ,String text){
        try {
            String sql=String.format("INSERT INTO `messages` (`datetime`, `sender`, `reciver`, `text`) VALUES (CURRENT_TIMESTAMP, '%s', '%s',\"%s\")",from,who,text);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
