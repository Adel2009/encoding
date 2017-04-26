
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class startCommuncation
{
  //  public static Connection getConnection() throws SQLException, ClassNotFoundException
  //  {
        //Class.forName("com.mysql.jdbc.Driver");

    //    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "CSCE741!");

      //  return connection;


        //Class.forName("com.mysql.jdbc.Driver").newInstance();
        //Connection connect= DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "CSCE741!");

        //Statement stat = connect.createStatement();




   // }



    public static Connection databaseConn() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {



        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection connect= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/students", "root", "CSCE741!");

        Statement stat = connect.createStatement();

        return connect;



    }



    public static void main(String[] args)
    {
        try
        {
            databaseConn();
        }
        catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}