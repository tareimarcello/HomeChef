package progettoispw.letmeknow.controller.search;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Query {
    protected ResultSet searchAll(Statement stmt,String iduser,int emp, int hum,int pos)  {
        try{String sql=String.format("SELECT userid " +
                        "FROM utenti WHERE type='usr' and " +
                        "empathy>=%d and humor>=%d and positivity >=%d and userid != '%s' ",
                        emp,hum,pos,iduser);
            return stmt.executeQuery(sql);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public boolean newLine(Statement stmt,String userid){
        try{
            String sql=String.format("INSERT INTO `recently_visited` SET userid ='%s' ",userid);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }
    public ResultSet getnVisit(Statement stmt,String userid){
        try{
        String sql=String.format("SELECT * from recently_visited where userid='%s' ",userid);
        return stmt.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public boolean incremVisit(Statement stmt,String userid){
        try{
            String sql=String.format("UPDATE `recently_visited` SET num_visit = num_visit+1 WHERE (`userid`='%s')",userid);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }
    public ResultSet getVisited(Statement stmt,String userid) {
        try{
            String sql=String.format("SELECT `visit1`,`visit2`,`visit3` FROM `recently_visited` WHERE `userid`='%s'",userid);
            return stmt.executeQuery(sql);
            } catch (SQLException throwables) {
            return null;
        }
    }
    public boolean setVisited(Statement stmt, String userid, String[] input) {
        try{
            String sql=String.format("UPDATE `recently_visited` SET `visit1` = '%s',`visit2` = '%s',`visit3` = '%s' WHERE `userid` = '%s' ",input[0],input[1],input[2],userid);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            return false;
        }
    }
    public ResultSet getnRows(Statement stmt) {
        try{
            String sql="SELECT COUNT(*) FROM recently_visited";
            return stmt.executeQuery(sql);
        } catch (SQLException throwables) {
            return null;
        }
    }
}
