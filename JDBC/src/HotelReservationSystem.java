import java.sql.*;
import java.util.Scanner;

public class HotelReservationSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "Vishal@07.";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded succesfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            while(true) {
                System.out.println(" ");
                System.out.println("HOTEL MANAGEMENT SYSTEM");
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Reserve a Room");
                System.out.println("2. View Reservation");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservation");
                System.out.println("5. Delete Reservation");
                System.out.println("0. Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        reserveRoom(connection, sc);
                        break;

                    case 2:
                        viewReservation(connection);
                        break;

                    case 3:
                        getRoomNumber(connection, sc);
                        break;

                    case 4:
                        updateReservation(connection, sc);
                        break;

                    case 5:
                        deletereservation(connection, sc);
                        break;

                    case 0:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try Again.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection failed:" + e.getMessage());
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

    }

    private static void reserveRoom(Connection connection, Scanner sc ){

        System.out.println("Enter Guest Name: ");
        String guest_name = sc.next();
        sc.nextLine();
        System.out.println("Enter Room Number: ");
        int room_number =sc.nextInt();
        System.out.println("Enter Contact Number ");
        int contact_number = sc.nextInt();

        String sql= "insert into reservation(guest_name,room_number,contact_number)" +
                "values('" + guest_name + "'," + room_number + ",'" + contact_number + "')";

        try(Statement statement=connection.createStatement()){
            int rowAffected = statement.executeUpdate(sql);

            if(rowAffected>0){
                System.out.println("Reservation Successfull");
            }
            else{
                System.out.println("Reservation Failed. Try Again");
            }

        }catch(SQLException e){
            System.out.println("Connection Failed"+e.getMessage());
        }

    }
    private static void viewReservation(Connection connection) throws SQLException{
        String Query="select * from reservation";
        try(Statement statement= connection.createStatement();
            ResultSet resultset= statement.executeQuery(Query)){

            System.out.println("Current Reservation");
            System.out.println("+-----------------+------------------+-----------------+-----------------+-----------------+");
            System.out.println("|  Reservation Id |    Guest Name    |   Room Number   | Contact Number  | Reservation_date|");
            System.out.println("+-----------------+------------------+-----------------+-----------------+-----------------+");

            while (resultset.next()) {

                int Reservation_ID = resultset.getInt("reservation_id");
                String Guest_Name = resultset.getString("guest_name");
                int Room_Number =resultset.getInt("room_number");
                String Contact_Number= resultset.getString("contact_number");
                String Reservation_date= resultset.getTimestamp("reservation_date").toString();

                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s |\n",
                        Reservation_ID, Guest_Name, Room_Number, Contact_Number, Reservation_date);
            }

            System.out.println("+-----------------+------------------+-----------------+-----------------+-----------------+");

        }
    }
    private static void getRoomNumber(Connection connection, Scanner sc ){
        try{
            System.out.println("Enter Reservation ID");
            int Reservation_id =sc.nextInt();
            System.out.println("Enter Guest Name");
            String Guest_Name = sc.next();

            String SQL = "select room_number from reservation" +
                    " where reservation_id ="+ Reservation_id +
                    " and guest_name ='"+Guest_Name+"'";


            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(SQL);

            if(resultSet.next()){

                int Room_Number=resultSet.getInt("room_number");
                System.out.println("The Room Number to the Reservation Id: "+Reservation_id+" And Guest Name: "+Guest_Name+" is Room Number: "+Room_Number);
            }
            else{
                System.out.println("Reservation not found for the reservation id and guest name");
            }

        }catch(SQLException e){
            System.out.println("Conncetion failed "+e.getMessage());
        }
    }

    private static void updateReservation(Connection connection, Scanner sc){

        try{
            System.out.println("For Updating Guest Name & Contact Number Enter Reservation ID:");
            int reservation_ID = sc.nextInt();
            sc.nextLine();

            if(!reservationExists(connection, reservation_ID)){
                System.out.println("Reservation not found for the given ID");
                return;
            }

            System.out.println("Enter Room Number");
            int RoomNumber= sc.nextInt();
            System.out.println("Enter Guest Name");
            String Guest_name=sc.next();
            System.out.println("Enter Contact Number");
            String Contact_number=sc.next();


            String Sql="update reservation set contact_number='"+Contact_number+"',"+
                    "room_number="+RoomNumber+","+
                    "guest_name='"+Guest_name+"' "+
                    "where reservation_id = " + reservation_ID;


            try(Statement statement=connection.createStatement()){
                int RowAffected= statement.executeUpdate(Sql);
                if(RowAffected>0){
                    System.out.println("Reservation Update successfully!!");
                }
                else{
                    System.out.println("Reservation Updated Failed");
                }
            }
        }catch(SQLException e){
            System.out.println("connection Failed"+e.getMessage());
        }

    }


    private static void deletereservation(Connection connection,Scanner sc){
        try{
            System.out.println("Enter Reservation Id to Delete ");
            int reservation_ID = sc.nextInt();
            sc.nextLine();

            if(!reservationExists(connection, reservation_ID)){
                System.out.println("Reservation not found for the given ID");
                return;
            }


            String Sql="delete from reservation where reservation_id ="+reservation_ID;

            try(Statement statement=connection.createStatement()){
                int RowAffected= statement.executeUpdate(Sql);
                if(RowAffected>0){
                    System.out.println("Reseervation Deleted successfully!!");
                }
                else{
                    System.out.println("Reseervation Deletion Failed");
                }
            }
        }catch(SQLException e){
            System.out.println("connection Failed"+e.getMessage());
        }

    }

    private static boolean reservationExists(Connection connection, int reservationId) {
        try{
            String sql= "select  reservation_id from reservation where reservation_id="+reservationId;

            try(Statement statement= connection.createStatement();
                ResultSet resultset= statement.executeQuery(sql)){

                return resultset.next();

            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;

        }
    }
    private static void exit() throws InterruptedException{

        System.out.print("Exiting System");
        int i=5;
        while ((i!=0)){
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println("");
        System.out.println("ThankYou for Using Hotel reservation System!!!");
    }
}






