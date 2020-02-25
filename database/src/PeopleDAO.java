import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.lang.System.*;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/PeopleDAO")
public class PeopleDAO {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public PeopleDAO() {

    }
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/project?"
  			          + "user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public List<Users> listAllPeople() throws SQLException {
        List<Users> listPeople = new ArrayList<Users>();        
        String sql = "SELECT * FROM login";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int id = resultSet.getInt("idLogin");
            String username = resultSet.getString("username");
            String password = resultSet.getString("pass");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String age = resultSet.getString("age");
             
            Users user = new Users(id, username, password, first_name, last_name, age);
            listPeople.add(user);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listPeople;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
         
    public boolean insert(Users user) throws SQLException {
    	connect_func();
    	PrintStream out = System.out;
		String sql = "insert into login(Username, Pass, First_name, Last_name, Age) values (?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, user.username);
		preparedStatement.setString(2, user.password);
		preparedStatement.setString(3, user.first_name);
		preparedStatement.setString(4, user.last_name);
		preparedStatement.setString(5, user.age);
		
		out.println("Start of User Info");
		out.println(user.username);
		out.println(user.password);
		out.println(user.first_name);
		out.println(user.last_name);
		out.println(user.age);
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowInserted;
    }     
     
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM login WHERE id = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowDeleted;     
    }
     
    public boolean update(People people) throws SQLException {
        String sql = "update login set Name=?, Address =?,Status = ? where id = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, people.name);
        preparedStatement.setString(2, people.address);
        preparedStatement.setString(3, people.status);
        preparedStatement.setInt(4, people.id);
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowUpdated;     
    }
	
    public People getPeople(int id) throws SQLException {
    	People people = null;
        String sql = "SELECT * FROM student WHERE id = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String status = resultSet.getString("status");
             
            people = new People(id, name, address, status);
        }
         
        resultSet.close();
        statement.close();
         
        return people;
    }
}
