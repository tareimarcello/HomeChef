package progettoispw.letmeknow.controller.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    protected ResultSet selectUser(Statement stmt, String iduser )throws SQLException {
        String sql=String.format(" SELECT * FROM utenti  where userid = '%s' ;",iduser);
        return stmt.executeQuery(sql);
    }
    protected ResultSet selectUser(Statement stmt,String what, String input )throws SQLException {
        String sql=String.format(" SELECT * FROM utenti  WHERE %s = '%s' ;",what,input);
    
        return stmt.executeQuery(sql);
    }
    protected boolean setDB(Statement stmt, String iduser ,String what,String edit){
        try {
            String sql=String.format(" UPDATE  utenti set %s='%s' WHERE userid = '%s' ;",what,edit,iduser);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected ResultSet queryUid(Statement stmt) throws SQLException {
        String sql=" SELECT userid FROM utenti ";
        return stmt.executeQuery(sql);
    }

    protected boolean newLine(Statement stmt,String [] log ,int [] val,String description,String goal) {

        try {
            String sql=String.format("INSERT INTO `utenti` (`userid`, `password`, `type`, `email`, `empathy`, `humor`, `positivity`, `description`, `goal`,`by`)" +
                            " VALUES ('%s',         '%s',      '%s',       '%s',    '%d',       '%d',      '%d','%s','%s',current_date+INTERVAL 6 month )",
                    log[0],log[1],log[2],log[3],val[0],val[1],val[2],description,goal);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
           return false;
        }
    }
    protected boolean newLine(Statement stmt, String uid,String password, String type, String email) {
        try {
            String sql=String.format("INSERT INTO `utenti` (`userid`, `password`, `type`, `email`)" +
                            " VALUES ('%s',         '%s',      '%s',       '%s')",
                    uid,password,type,email);

            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    protected boolean feed(Statement stmt,String from,String what){
        try {
            String sql=String.format(" INSERT INTO suggest (`from`, `content`,`when`) VALUES ('%s', '%s',CURRENT_TIMESTAMP);",from,what);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
