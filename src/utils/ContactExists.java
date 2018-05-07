package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import beans.Contact;
import beans.User;
import dbManipulation.ConnectionManager;

public class ContactExists {

	static Connection conn=ConnectionManager.getInstance().getConnection();
	
	public static boolean checkIfContactExists(Contact contact,User user) throws SQLException{
		
		String query="SELECT * FROM contact WHERE contactId=? AND userId=?";
		ResultSet rs=null;
		
		try(
			PreparedStatement statement=conn.prepareStatement(query);
				){
			
			statement.setInt(1, contact.getContactId());
			statement.setInt(2, user.getUserId());
			
			rs=statement.executeQuery();
			
			if(rs.next()){
				rs.close();
				return true;
			}else{
				System.out.println("That contact does not exist.");
				return false;
			}
		}
		
	}
	
	
}
