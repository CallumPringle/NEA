package Tasks;
import java.sql.*;
import java.time.LocalDate;

public class TasksDatabase {
    public static ResultSet retrieveUsername(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://src/Tasks.accdb");//Establishing Connection
            Statement stmt = conn.createStatement();
            String selTable = "select username from loginDetails";
            stmt.execute(selTable);
            return stmt.getResultSet();
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public static ResultSet retrievePassword(String username)  {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://src/Tasks.accdb");//Establishing Connection
            Statement stmt = conn.createStatement();
            String q = "select password from loginDetails where username= '"+username+"'";
            stmt.execute(q);
            return stmt.getResultSet();}
        catch(Exception e){
            System.out.println(e);
            return null;
        }

    }
    public static ResultSet loadTasks(String username){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://src/Tasks.accdb");//Establishing Connection
            Statement stmt = conn.createStatement();
            String selTable = "select taskDetail,taskDate from tasksTable where username= '"+username+"'";
            stmt.execute(selTable);
            return stmt.getResultSet();

        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    public static void registerUser(String username,String password){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://src/Tasks.accdb");//Establishing Connection
            PreparedStatement pstmt = conn.prepareStatement("insert into loginDetails (username,password) values('"+username+"','"+password+"')");
            pstmt.executeUpdate();
            System.out.println("details added");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void tasksIntoDatabase(String task, tasks tasks, String username){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://src/Tasks.accdb");//Establishing Connection
            PreparedStatement pstmt = conn.prepareStatement("insert into tasksTable (ID,taskDetail,taskDate,username) values(null,'"+task+"','"+LocalDate.of(tasks.getYear(),tasks.getMonth(),tasks.getDay())+"','"+username+"')");
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
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://src/Tasks.accdb");//Establishing Connection
            PreparedStatement pstmt = conn.prepareStatement("delete from tasksTable where taskDetail= '"+task+"'");
            pstmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}