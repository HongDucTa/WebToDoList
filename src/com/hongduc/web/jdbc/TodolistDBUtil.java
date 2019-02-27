package com.hongduc.web.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TodolistDBUtil {
	private DataSource dataSource;

	public TodolistDBUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Todo> getTodos() throws Exception
	{/* Retourne la liste des todos */
		List<Todo> listeTodos = new ArrayList<Todo>();
		Connection connection=null;
		Statement statement = null;
		ResultSet result= null;
		try
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String query = "select * from todo order by date DESC";
			result = statement.executeQuery(query);
			while (result.next())
			{
				int id = result.getInt("id");
				String description = result.getString("description");
				LocalDate date = result.getDate("date").toLocalDate();
				Todo todo = new Todo(id,description,date);
				listeTodos.add(todo);
			}
			return listeTodos;
		}
		finally
		{
			close(connection,statement,result);
		}
	}
	
	private void close(Connection connection, Statement statement, ResultSet result)
	{ /* Ferme la connexion à la base de données */
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
	
	public void addTodo(Todo todo)
	{/* Ajoute un todo */
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try
		{
			connection = dataSource.getConnection();
			String query = "insert into todo values (null,?,?)";
			statement = connection.prepareStatement(query);
			String description = todo.getDescription();
			LocalDate date = LocalDate.now();
			statement.setString(1,description);
			statement.setDate(2, Date.valueOf(date));
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
	
	public void editTodo(Todo todo)
	{ /* Editer un todo */
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try
		{
			connection = dataSource.getConnection();
			String query = "update todo set description = ?,date = ? where id = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1,todo.getDescription());
			statement.setDate(2, Date.valueOf(todo.getDate()));
			statement.setInt(3,todo.getId());
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
	
	public void deleteTodo(int id)
	{ /* Supprimer un todo */
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String query = "delete from todo where id =" + id;
			statement.execute(query);
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
	
	public Todo fetchTodoById(int id)
	{ /* Recherche un todo dans la base de données d'après son id.
	Retourne null s'il n'y en a pas.
	*/
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		Todo todo = null;
		try
		{
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			String query = "select * from todo where id="+ id;
			result = statement.executeQuery(query);
			while (result.next())
			{
				String description = result.getString("description");
				LocalDate date = result.getDate("date").toLocalDate();
				todo = new Todo(id,description,date);
			}
			return todo;
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
	
}
