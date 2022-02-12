package fdms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class dbconn {
    public Connection conn=null;
    public Statement stmt=null;
    public dbconn(){
        try {
            String driverName="oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);
            String serverName="localhost";
            String serverPort="1521";
            String sid="orcl";
            String url="jdbc:oracle:thin:@"+serverName+":"+serverPort+":"+sid;
            String username="scott";
            String password="tiger";
            conn=DriverManager.getConnection(url,username,password);
            stmt=conn.createStatement();
        }
        catch(ClassNotFoundException e){
            System.out.println("Driver not found:"+e.getMessage());
        }
        catch(SQLException e) {
            System.out.println("Could not connect to database."+e.getMessage());
        }
    }
    public void end() throws SQLException
    {
        stmt.close();
        conn.close();
    }
}
