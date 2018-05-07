package main;

import java.sql.SQLException;

import beans.Contact;
import beans.User;
import dbManipulation.DataManipulation;
import dbManipulation.Login;
import dbManipulation.Register;
import utils.ContactExists;
import utils.Input;

public class AppMain {

	public static void main(String[] args) throws SQLException {
		
		System.out.println("Welcome!Choose one of the following options:");
		System.out.println("1. Sign Up");
		System.out.println("2. Log In");

		int choise=Input.getInt();
	
		if(choise==1){
			Register reg=new Register();
			reg.registration();
		}else if(choise==2){
			User user=new User();
			Login login=new Login();
			
			System.out.println("Enter your user name: ");
			String fName=Input.getString();
			user.setFirstName(fName);
			
			System.out.println("Enter your password");
			String pass=Input.getString();
			user.setPassword(pass);
			
			boolean loged=login.login(user);
			
			if(loged){
				Contact contact=new Contact();
				System.out.println("You can choose one of the following options:");
				System.out.println("1. Add new contact:");
				System.out.println("2. Edit contact:");
				System.out.println("3. Delete contact:");
				System.out.println("4. Show all contacts:");
				System.out.println("5. Search by name:");
				int regChoise=Input.getInt();
				
				switch (regChoise) {
				case 1:
					DataManipulation.addNewContact(user);
					break;
				case 2:
					System.out.println("Enter the ID of a contact you want to edit: ");
					int conId=Input.getInt();
					contact.setContactId(conId);
					
					boolean exists=ContactExists.checkIfContactExists(contact,user);
					if(exists){
						DataManipulation.editContact(contact); 
					}
					break;
				case 3:
					System.out.println("Enter the ID of a contact you want to delete: ");
					int contId=Input.getInt();
					contact.setContactId(contId);
					
					boolean exist=ContactExists.checkIfContactExists(contact, user);
					if(exist){
						DataManipulation.deleteContact(contact);
					}
					break;
				case 4:
					DataManipulation.showAllContacts(user);
					break;
				case 5:
					DataManipulation.searchByName(user);
					break;
				default:
					System.out.println("Invalid input.Try again.");
				}
				
				
			}else{
				System.out.println("You are not registered.");
			}
		}else{
			System.out.println("Invalid input.Try again.");
		}
		
	}

}
