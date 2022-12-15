
import java.sql.*;
import java.time.LocalDate;

public class TasksDatabase {
    public static ResultSet loadTasks(){
        try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
        Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\pring\\IdeaProjects\\NEA\\calendar\\src\\Tasks.accdb");//Establishing Connection
        Statement stmt = conn.createStatement();
        String selTable = "select taskDetail,taskDate from tasksTable";
        stmt.execute(selTable);
        return stmt.getResultSet();

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static void tasksIntoDatabase(String task,tasks tasks){
        try {
            LocalDate.of(2004,12,31);
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\pring\\IdeaProjects\\NEA\\calendar\\src\\Tasks.accdb");//Establishing Connection
            PreparedStatement pstmt = conn.prepareStatement("insert into tasksTable (ID,taskDetail,taskDateTime) values(null,'"+task+"','"+LocalDate.of(tasks.getYear(),tasks.getMonth(),tasks.getDay())+"')");
            pstmt.executeUpdate();
            System.out.println("task added");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void deleteTask(String task){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\pring\\IdeaProjects\\NEA\\calendar\\src\\Tasks.accdb");//Establishing Connection
            PreparedStatement pstmt = conn.prepareStatement("delete from tasksTable where taskDetail= '"+task+"'");
            pstmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
