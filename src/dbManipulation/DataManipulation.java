package dbManipulation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Contact;
import beans.User;
import utils.Input;

public class DataManipulation {

	static Connection conn=ConnectionManager.getInstance().getConnection();
	
	public static void addNewContact(User user) throws SQLException{
		
		String query="INSERT INTO contact(contactName, phoneNumber, userId) "
				+ "SELECT ?,?, userId FROM user WHERE firstName=? AND password=? ;";
		
		System.out.println("Enter contact name: ");
		String contactName=Input.getString();
		
		System.out.println("Enter phone number: ");
		String phoneNumber=Input.getString();
		
		try(
			PreparedStatement statement=conn.prepareStatement(query);
				){
			
			statement.setString(1, contactName);
			statement.setString(2, phoneNumber);
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getPassword());
			
			statement.executeUpdate();
			
			System.out.println("Contact added.");
		}
		
	}
	
	public static void editContact(Contact contact) throws SQLException{ 
		
		String query="UPDATE contact SET contactName=?, phoneNumber=? WHERE contactId=? ;"; 
		
		System.out.println("Enter new name for the contact: ");
		String nName=Input.getString();

		System.out.println("Enter new phone number for the contact: ");
		String phone=Input.getString();
		
		try(
			PreparedStatement statement=conn.prepareStatement(query);
				){
			
			statement.setString(1, nName);
			statement.setString(2, phone);
			statement.setInt(3, contact.getContactId());
			
			statement.executeUpdate();
			
			System.out.println("Contact updated.");
		}
		
	}
	
	public static void deleteContact(Contact contact) throws SQLException{
		
		String query="DELETE FROM contact WHERE contactId=?";
		
		try(
			PreparedStatement statement=conn.prepareStatement(query);
				){
			
			statement.setInt(1, contact.getContactId());
			
			statement.executeUpdate();
			System.out.println("Contact deleted.");
		}
		
	}
	
	public static void showAllContacts(User user) throws SQLException{
		
		String query="SELECT * FROM contact WHERE userId=?";
		ResultSet rs=null;
		
		try(
			PreparedStatement statement=conn.prepareStatement(query);	
				){
			
			statement.setInt(1, user.getUserId());
			rs=statement.executeQuery();
			
			if(!rs.isBeforeFirst()){
				System.out.println("You don't have any contacts.");
			}else{
				while(rs.next()){
					System.out.println("Contact ID: "+rs.getInt("contactId")+", contact name: "+rs.getString("contactName")+
							", phone number: "+rs.getString("phoneNumber"));
				}
			}
			rs.close();
		}
	}
	
	public static void searchByName(User user) throws SQLException{
		
		String query="SELECT * FROM contact WHERE contactName LIKE ? AND userId=?;";
		
		System.out.println("Enter the name: ");
		String searchName=Input.getString();
		
		ResultSet rs=null;
		
		try(
				PreparedStatement statement=conn.prepareStatement(query);	
					){
				
				statement.setString(1, searchName);
				statement.setInt(2, user.getUserId());
				rs=statement.executeQuery();
				
				if(!rs.isBeforeFirst()){
					System.out.println("No contacts to show.");
				}else{
					while(rs.next()){
						System.out.println("Contact ID: "+rs.getInt("contactId")+", contact name: "+rs.getString("contactName")+
								", phone number: "+rs.getString("phoneNumber"));
					}
				}
				rs.close();
			}
	}
		
}
