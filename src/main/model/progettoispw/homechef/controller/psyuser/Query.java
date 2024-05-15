package progettoispw.letmeknow.controller.psyuser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    protected ResultSet selectResult(Statement stmt,int month,int year )throws SQLException {
        String sql=String.format(" SELECT * FROM forms WHERE YEAR(`by`)=%d AND MONTH(`by`)=%d ",year,month);
        return stmt.executeQuery(sql);
    }
    protected boolean suggestForm(Statement stmt,String from,String what)  {
        try {
            String sql=String.format(" INSERT INTO suggest (`from`, `content`,`when`,`type`) VALUES ('%s', '%s',CURRENT_TIMESTAMP,'psy');",from,what);
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
