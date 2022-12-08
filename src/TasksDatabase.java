
import java.sql.*;
public class TasksDatabase {
    public static ResultSet loadTasks(){
        try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
        Connection conn = DriverManager.getConnection("jdbc:ucanaccess://X:\\computer science\\java projects\\NEA\\src\\Tasks.accdb");//Establishing Connection
        Statement stmt = conn.createStatement();
        String selTable = "select taskDetail from tasksTable";
        stmt.execute(selTable);
        return stmt.getResultSet();

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static void tasksIntoDatabase(String task, tasks tasks){
        try {
            Date test = new Date(tasks.getDay(),tasks.getMonth(),tasks.getYear());
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://X:\\computer science\\java projects\\NEA\\src\\Tasks.accdb");//Establishing Connection
            PreparedStatement pstmt = conn.prepareStatement("insert into tasksTable (ID,taskDetail,taskDateTime) values(null,'"+task+"','"+test.getTime()+"')");
            pstmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void deleteTask(String task){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://X:\\computer science\\java projects\\NEA\\src\\Tasks.accdb");//Establishing Connection
            PreparedStatement pstmt = conn.prepareStatement("delete from tasksTable where taskDetail= '"+task+"'");
            pstmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
