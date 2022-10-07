import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
public class tasksDatabase {
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tasks","root","root");
//here tasks is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from current_tasks");
            while(rs.next())
                System.out.println(rs.getString(1)+"  "+rs.getDate(2));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

}