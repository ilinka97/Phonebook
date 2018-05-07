package dbManipulation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import beans.User;

public class Login {

	Connection conn=ConnectionManager.getInstance().getConnection();
	
	public boolean login(User user) throws SQLException{
		
		String query="SELECT * FROM user WHERE firstName=? AND password=? ";
		ResultSet rs=null;
		
		try(
			PreparedStatement statement=conn.prepareStatement(query);
				){
			
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getPassword());
			
			rs=statement.executeQuery();
			
			if(rs.next()){
				if(user.getFirstName().equals(rs.getString("firstName")) && user.getPassword().equals(rs.getString("password"))){
					user.setUserId(rs.getInt("userId"));
					rs.close();
					return true;
				}	
			}
			return false;
		}
		
	}
	
	
}
