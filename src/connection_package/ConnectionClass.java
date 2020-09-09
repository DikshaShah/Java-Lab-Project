package connection_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

	public static Connection doConnect() 
	{
		try
		{
		   String driver = "com.mysql.cj.jdbc.Driver";
		   String url  = "jdbc:mysql://localhost:3306/javaproject";
		   String username = "root";
		   String password = "4689";
		   Class.forName(driver); //class not found
		   Connection conn = DriverManager.getConnection(url,username,password);//No suitable driver found
		   System.out.println("Connected...");
		   return conn;
		}
		catch(SQLException e)
		{
			System.out.println("No suitable driver found");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("class not found");
		}
			return null;
	}

}