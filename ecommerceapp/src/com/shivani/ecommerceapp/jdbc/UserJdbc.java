package com.shivani.ecommerceapp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;
import com.shivani.ecommerceapp.entity.User;

public class UserJdbc {
	private Driver driver;
	private Connection connection;
	private PreparedStatement preparedstatement;
	private ResultSet resultset;
	private String query;
	private void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		driver=new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/e_app", "root", "root");
		
		 
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	private void closeConnection() {
		if(preparedstatement!=null) {
			try {
				preparedstatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(driver!=null) {
			try {
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void addUser(User user) {
		openConnection();
		query="Insert into user values(?,?,?,?,?,?)";
		try {
			preparedstatement=connection.prepareStatement(query);
			preparedstatement.setInt(1, user.getId());
			preparedstatement.setString(2, user.getName());
			preparedstatement.setString(3,user.getEmail());
			preparedstatement.setLong(4,user.getMobile());
			preparedstatement.setString(5, user.getPassword());
			preparedstatement.setString(6,user.getRole());
			int res=preparedstatement.executeUpdate();
			System.out.println(res + "row(s) selected");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void findUserById(int id) {
		openConnection();
		query="select *from user where id= ?";
		try {
			preparedstatement=connection.prepareStatement(query);
			preparedstatement.setInt(1, id);
			resultset=preparedstatement.executeQuery();
			if(resultset.next()) {
				System.out.println(resultset.getInt(1));
				System.out.println(resultset.getString(2));
				System.out.println(resultset.getString(3));
				System.out.println(resultset.getLong(4));
				System.out.println(resultset.getString(5));
				System.out.println(resultset.getString(6));
				
			} else
				System.out.println("user not found");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void findAllUsers() {
		openConnection();
		query="select * from user";
		
		try {
			preparedstatement =connection.prepareStatement(query);
			resultset=preparedstatement.executeQuery();
			int count=0;
			while(resultset.next()) {
				count++;
				System.out.println(resultset.getInt("id"));
				System.out.println(resultset.getString("name"));
				System.out.println(resultset.getString("email"));
				System.out.println(resultset.getLong("mobile"));
				System.out.println(resultset.getString("password"));
				System.out.println(resultset.getString("role"));
			}
			if(count==0) {
				System.out.println("User not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		
	}
	
	public void findUserByEmailAndPassword(String email,String password) {
	openConnection();
	query="select * from user where email=? AND password=?";
	 try {
		preparedstatement=connection.prepareStatement(query);
		preparedstatement.setString(1, email);
		preparedstatement.setString(2, password);
		resultset=preparedstatement.executeQuery();
		if(resultset.next()) {
			System.out.println("User found");
			System.out.println(resultset.getInt("id"));
			System.out.println(resultset.getString("name"));
			System.out.println(resultset.getString("email"));
			System.out.println(resultset.getLong("mobile"));
			System.out.println(resultset.getString("password"));
			System.out.println(resultset.getString("role"));
		}else {
			System.out.println("Invalid email or password");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	public void deleteUser(int id) {
		openConnection();
		query="delete from user where id=?";
		try {
			preparedstatement=connection.prepareStatement(query);
			preparedstatement.setInt(1, id);
			int res=preparedstatement.executeUpdate();
			System.out.println(res +" row(s) affected");
			if(res !=0)
				System.out.println("User deleted");
			else
				System.out.println("invalid user id");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		
		
	}
	public void UpdateUser(int id) {
		openConnection();
		query="update user set password=? where id=?  ";
		try {
			preparedstatement=connection.prepareStatement(query);
			preparedstatement.setInt(1,id);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
