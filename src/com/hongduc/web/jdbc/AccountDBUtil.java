package com.hongduc.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class AccountDBUtil {
	private DataSource dataSource;
	
	public AccountDBUtil(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	public Account fetchAccountByUsername(String username)
	{ /* Recherche un Account dans la base de données d'après le username.
	Retourne null s'il n'y en a pas.
	*/
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		Account account = null;
		try
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String query = "select * from account where username=\"" + username + "\"";
			result = statement.executeQuery(query);
			while (result.next())
			{
				String password = result.getString("password");
				String role = result.getString("role");
				account = new Account(username,password,role);
			}
			return account;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		finally
		{
			close(connection,statement,result);
		}
	}
	
	private void close(Connection connection, Statement statement, ResultSet result)
	{ /* ferme la connexion à la base de données */
		try{
			if(statement!=null)
				statement.close();
			if(result!=null)
				result.close();
			if(connection!=null)
				connection.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void editPassword(Account account)
	{ /* Permet d'éditer le mot de passe. Fonctionnalité non implémentée
	*/
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try
		{
			connection = dataSource.getConnection();
			String query = "update account set password = ? where username = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,account.getUsername());
			statement.setString(2,account.getUsername());
			statement.execute();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			close(connection,statement,result);
		}
	}
}
