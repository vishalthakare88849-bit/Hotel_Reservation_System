import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Connection connection;
    private Scanner scanner;

    public User(Connection connection,Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }

    public void register(){
        scanner.nextLine();
        System.out.print("Name:");
        String Full_name= scanner.nextLine();
        System.out.print("Email:");
        String Email= scanner.nextLine();
        System.out.println("Password:");
        String Password= scanner.nextLine();
        if(user_exits(Email)){
            System.out.println("User Already exits for this Email Address!!");
            return;
        }
        String sql="insert into user values(?,?,?)";
        try{
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,Full_name);
        preparedStatement.setString(2,Email);
        preparedStatement.setString(3,Password);
        int RowAffected = preparedStatement.executeUpdate();
        if(RowAffected>0){
            System.out.println("Registration Succesfully !!");
        }
        else{
            System.out.println("Registration Failed!! ");
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public String login(){
        scanner.nextLine();
        System.out.println("Email:");
        String Email = scanner.nextLine();
        System.out.println("Password:");
        String PassWord=scanner.nextLine();
        String sql="select *from user where email=? and password=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,Email);
            preparedStatement.setString(2,PassWord);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return Email;
            }
            else {
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();

        }
        return null;
    }


    public boolean user_exits(String Email){
        String sql="select *from user where email=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,Email);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else {
                return false;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
