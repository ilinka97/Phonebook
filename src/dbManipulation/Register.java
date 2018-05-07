package dbManipulation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.Input;

public class Register {

	Connection conn=ConnectionManager.getInstance().getConnection();
	
	public void registration() throws SQLException{
		
		String query="INSERT INTO user(firstName,lastName,password) VALUES (?,?,?)";
		
		System.out.println("Enter your first name: ");
		String firstName=Input.getString();
		
		System.out.println("Enter your last name: ");
		String lastName=Input.getString();
		
		System.out.println("Enter your password: ");
		String password=Input.getString();
		
		
		try(
			PreparedStatement statement=conn.prepareStatement(query);
				){
			
			statement.setString(1,firstName);
			statement.setString(2,lastName);
			statement.setString(3,password);
			
			statement.executeUpdate();
			System.out.println("You are registered.");
			
		}
	}
}
