import java.sql.*;
public class tasksAccessDatabase {
    public static void main(String[] args) {
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");//Loading Driver
            Connection connection= DriverManager.getConnection("jdbc:ucanaccess://X:\\computer science\\java projects\\NEA\\src\\Tasks.accdb");//Establishing Connection
            System.out.println("Connected Successfully");

        }catch(Exception e){
            System.out.println("Error in connection" + e);

        }
}}
