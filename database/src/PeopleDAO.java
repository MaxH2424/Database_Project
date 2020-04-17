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

import java.util.Date;
import java.util.Calendar;
import java.time.*;
import java.time.format.DateTimeFormatter; 
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
            String url = "jdbc:mysql://127.0.0.1:3306/project?useTimezone=true&serverTimezone=UTC";
            String name ="john";
            String pass = "pass1234";
            connect = (Connection) DriverManager
  			      .getConnection(url,name,pass);
            System.out.println(connect);
        }
    }
    
    public List<Users> listAllPeople() throws SQLException {
        List<Users> listPeople = new ArrayList<Users>();        
        String sql = "SELECT * FROM users";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String username = resultSet.getString("Username");
            String age = resultSet.getString("Age");
            String gender = resultSet.getString("Gender");
            String first_name = resultSet.getString("First_name");
            String last_name = resultSet.getString("Last_name");
            String pass = resultSet.getString("pass");
            String favorites = resultSet.getString("favorites");
            
            int favorite = Integer.parseInt(favorites);
             
            Users user = new Users(username, age, first_name, last_name, pass, gender,favorite);
            listPeople.add(user);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listPeople;
    }
    
    public List<Videos> listAllVideos() throws SQLException {
        List<Videos> listVideos = new ArrayList<Videos>();        
        String sql = "SELECT * FROM videos";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String url = resultSet.getString("URL");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Descript");
            java.sql.Date date = resultSet.getDate("upload_date");
            String tags = resultSet.getString("Tags");
            int comedianID = resultSet.getInt("Comedian_ID");
            String comment = resultSet.getString("comment");
            String userID = resultSet.getString("UserID");
           
            Videos video = new Videos(url, title, description, tags, comment, userID, date);
            listVideos.add(video);
        }        
        resultSet.close();
        statement.close();         
        disconnect();        
        return listVideos;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
         
    public boolean insertPeopleQuery (Users user) throws SQLException {
    	connect_func();
    	PrintStream out = System.out;
		String sql = "insert into users(Username, Age, Gender, First_name, Last_name, Pass, Favorites) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, user.username);
		preparedStatement.setString(2, user.age);
		preparedStatement.setString(3, user.gender);
		preparedStatement.setString(4, user.first_name);
		preparedStatement.setString(5, user.last_name);
		preparedStatement.setString(6, user.password);
		preparedStatement.setInt(7, user.favorite);
		
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
    
    public boolean insertVideoQuery (Videos video) throws SQLException {
    	connect_func();
    	PrintStream out = System.out;
		String sql = "insert into Videos(Descript, upload_date, title, tags, URL, comedian_id) values (?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, video.description);
		preparedStatement.setDate(2, video.postDate);
		preparedStatement.setString(3, video.title);
		preparedStatement.setString(4, video.tags);
		preparedStatement.setString(5, video.url);
		preparedStatement.setInt(6, video.id);
		
		out.println("Start of Video Info");
		out.println(video.url);
		out.println(video.postDate);
		out.println(video.description);
		out.println(video.title);
		out.println(video.tags);
		out.println(video.id);
		
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowInserted; 
    }  
    
    public boolean insertCommentQuery(Comments comment)throws SQLException{
    	connect_func();
    	PrintStream out = System.out; // For debugging purposes
    	String sql = "insert into Review(Score, Remark, URL_Review, User_FK) values (?,?,?,?)";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, comment.rating);
		preparedStatement.setString(2, comment.comment);
		preparedStatement.setString(3, comment.url);
		preparedStatement.setInt(4, comment.comedianID);
		
		out.println("Start of Comment Info");
		out.println(comment.rating);
		out.println(comment.comment);
		out.println(comment.url);
		out.println(comment.comedianID);
		
		boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowInserted; 
    	
    }
    
    public boolean checkUser(Users user) throws SQLException{
    	connect_func();
    	String sql= "SELECT * FROM users WHERE username = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, user.username);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	
    	if (resultSet.next()) {
    		return true; 		
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean checkVideo(Videos video) throws SQLException{
    	connect_func();
    	String sql= "SELECT * FROM videos WHERE url = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, video.url);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	
    	if (resultSet.next()) {
    		return true; 		
    	}
    	else {
    		return false;
    	} 
    }
    
    public boolean checkLogin(String user, String pass) throws SQLException{
    	connect_func();
    	String sql= "SELECT username FROM users WHERE username = ? && pass = ?";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	preparedStatement.setString(1, user);
    	preparedStatement.setString(2, pass);
    	ResultSet resultSet = preparedStatement.executeQuery();
    	
    	if (resultSet.next()) {
    		return true; 		
    	}
    	else {
    		return false;
    	}
    	
    }
    
    public void initializeDatabase() throws SQLException{
    	connect_func();
    	
       	String sql = "DROP DATABASE project";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	int resultSet = preparedStatement.executeUpdate();
    	
    	String sql2 = "CREATE DATABASE project";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql3 = "USE project"; 
  
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql3);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql4 = "CREATE TABLE Comedian(\r\n" + 
    			"	First_name varchar(30),\r\n" + 
    			"	Last_name varchar(30),\r\n" + 
    			"	Birthday DATE,\r\n" + 
    			"	Birthplace varchar(90),\r\n" + 
    			"	ID INTEGER,\r\n" + 
    			"	PRIMARY KEY (ID)\r\n" + 
    			");";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql4);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql5 = "CREATE TABLE Favorite_Comedians(\r\n" + 
    			"	email varchar(90),\r\n" + 
    			"	comedianID INTEGER,\r\n" + 
    			"    PRIMARY KEY(comedianID)\r\n" + 
    			");";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql5);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql6 = "CREATE TABLE Videos(\r\n" + 
    			"	Descript varchar(200),\r\n" + 
    			"	upload_date Date,\r\n" + 
    			"	Title varchar(90),\r\n" + 
    			"	Tags varchar(90),\r\n" + 
    			"	URL varchar(200),\r\n" + 
    			"	Comedian_ID int,\r\n" + 
    			"	PRIMARY KEY(URL),\r\n" + 
    			"    FOREIGN KEY(Comedian_ID) REFERENCES Comedian(ID)\r\n" + 
    			");";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql6);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql7 = "CREATE TABLE Users(\r\n" + 
    			"	Username varchar(90),\r\n" + 
    			"	Age INTEGER,\r\n" + 
    			"	Gender varchar(30),\r\n" + 
    			"	First_name varchar(30),\r\n" + 
    			"	Last_name varchar(30),\r\n" + 
    			"	Pass varchar(30),\r\n" + 
    			"	Favorites INTEGER,\r\n" + 
    			"	PRIMARY KEY (Username),\r\n" + 
    			"	FOREIGN KEY (Favorites) References Favorite_Comedians(comedianID)\r\n" + 
    			"	\r\n" + 
    			");";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql7);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql8 = "CREATE TABLE Review(\r\n" + 
    			"	Score varchar(20),\r\n" + 
    			"	Remark varchar(200),\r\n" + 
    			"	URL_Review varchar(200),\r\n" + 
    			"	User_FK varchar(90),\r\n" + 
    			"	FOREIGN KEY (URL_Review) References Videos(URL),\r\n" + 
    			"	FOREIGN KEY (User_FK) References Users(username),\r\n" + 
    			"	CHECK (Score in ('Poor', 'Fair', 'Good', 'Excellent'))\r\n" + 
    			");";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql8);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String foreignKeySql = "SET FOREIGN_KEY_CHECKS=0";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(foreignKeySql);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql9 = "INSERT INTO Videos (Descript, upload_date, title, tags, url, comedian_ID)\r\n" + 
    			"VALUES ('Youtube', '1999/02/22', 'Things to Do', 'fun,exciting,cool', 'www.youtube.com', 1);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql9);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql10 = "INSERT INTO Comedian (first_name, last_name, birthday, birthplace, id)\r\n" + 
    			"VALUES ('Craig', 'Smith', '2006/07/20', 'New York', 1);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql10);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql11 = "INSERT INTO Comedian (first_name, last_name, birthday, birthplace, id)\r\n" + 
    			"VALUES ('John', 'Smith', '2005/07/20', 'New York', 2);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql11);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql12 = "INSERT INTO Comedian (first_name, last_name, birthday, birthplace, id)\r\n" + 
    			"VALUES ('Tom', 'Smith', '2004/07/20', 'New York', 3);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql12);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql13 = "INSERT INTO Comedian (first_name, last_name, birthday, birthplace, id)\r\n" + 
    			"VALUES ('Bill', 'Smith', '2003/07/20','New York', 4);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql13);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql14 = "INSERT INTO Comedian (first_name, last_name, birthday, birthplace, id)\r\n" + 
    			"VALUES ('Rick', 'Smith', '2002/07/20', 'New York', 5);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql14);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql15 = "INSERT INTO Comedian (first_name, last_name, birthday, birthplace, id)\r\n" + 
    			"VALUES ('Lang', 'Smith', '2001/07/20', 'New York', 6);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql15);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql16 = "INSERT INTO Comedian (first_name, last_name, birthday, birthplace, id)\r\n" + 
    			"VALUES ('Trang', 'Smith', '2011/07/20', 'New York', 7);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql16);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql17 = "INSERT INTO Comedian (first_name, last_name, birthday, birthplace, id)\r\n" + 
    			"VALUES ('pyong', 'Smith', '2008/07/20', 'New York', 8);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql17);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql18 = "INSERT INTO Comedian (first_name, last_name, birthday, birthplace, id)\r\n" + 
    			"VALUES ('Leon', 'Smith', '2009/07/20', 'New York', 9);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql18);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql19 = "INSERT INTO Comedian (first_name, last_name, birthday, birthplace, id)\r\n" + 
    			"VALUES ('Rictor', 'Smith', '2012/07/20', 'New York', 10);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql19);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql20 = "INSERT INTO Users (Username, Age, Gender, First_name, Last_name, Pass, Favorites)\r\n" + 
    			"VALUES ('ballzack69', 23, 'Male', 'Zachary', 'Taylor', '6969', 1);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql20);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql21 = "INSERT INTO Users (Username, Age, Gender, First_name, Last_name, Pass, Favorites)\r\n" + 
    			"VALUES ('person9', 24, 'Male', 'Jack', 'Taylor', 'yarp', 2);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql21);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql22 = "INSERT INTO Users (Username, Age, Gender, First_name, Last_name, Pass, Favorites)\r\n" + 
    			"VALUES ('person3', 25, 'Male', 'Lon', 'Ru', '699', 3);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql22);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql23 = "INSERT INTO Users (Username, Age, Gender, First_name, Last_name, Pass, Favorites)\r\n" + 
    			"VALUES ('person4', 20, 'Female', 'Abby', 'Hendrix', 'pass1234', 4);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql23);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql24 = "INSERT INTO Users (Username, Age, Gender, First_name, Last_name, Pass, Favorites)\r\n" + 
    			"VALUES ('person5', 10, 'Female', 'Carly', 'Wen', 'notpassword', 5);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql24);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql25 = "INSERT INTO Users (Username, Age, Gender, First_name, Last_name, Pass, Favorites)\r\n" + 
    			"VALUES ('person6', 29, 'Female', 'Jane', 'Fonda', 'yespassword', 6);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql25);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql26 = "INSERT INTO Users(Username, Age, Gender, First_name, Last_name, Pass, Favorites)\r\n" + 
    			"VALUES ('person7', 20, 'Female', 'Rick', 'Bobby', 'pass1235', 7);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql26);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql27 = "INSERT INTO Users (Username, Age, Gender, First_name, Last_name, Pass, Favorites)\r\n" + 
    			"VALUES ('person8', 21, 'Female', 'Regina', 'Babby', 'pass1236', 8);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql27);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql28 = "INSERT INTO Users (Username, Age, Gender, First_name, Last_name, Pass, Favorites)\r\n" + 
    			"VALUES ('person2', 20, 'Male', 'Rick', 'Barbara', 'pass1237', 9);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql28);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql29 = "INSERT INTO Users (Username, Age, Gender, First_name, Last_name, Pass, Favorites)\r\n" + 
    			"VALUES ('person1', 20, 'Female', 'Rachel', 'Grood', 'pass1238', 10);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql29);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql30 = "INSERT INTO Review (Score, Remark, URL_Review, User_FK)\r\n" + 
    			"VALUES ('Poor', 'This guy sucks', 'youtube.com/1', 1);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql30);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql31 = "INSERT INTO Review (Score, Remark, URL_Review, User_FK)\r\n" + 
    			"VALUES ('Fair', 'This guy sucks', 'youtube.com/1', 2);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql31);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql32 = "INSERT INTO Review (Score, Remark, URL_Review, User_FK)\r\n" + 
    			"VALUES ('Good', 'This guy good', 'youtube.com/2', 1);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql32);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql33 = "INSERT INTO Review (Score, Remark, URL_Review, User_FK)\r\n" + 
    			"VALUES ('Poor', 'I hate this person', 'youtube.com/5', 3);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql33);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql34 = "INSERT INTO Review (Score, Remark, URL_Review, User_FK)\r\n" + 
    			"VALUES ('Excellent', 'Funny', 'youtube.com/2', 2);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql34);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql35 = "INSERT INTO Review (Score, Remark, URL_Review, User_FK)\r\n" + 
    			"VALUES ('Good', 'Pretty sweet', 'youtube.com/4', 1);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql35);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql36 = "INSERT INTO Review (Score, Remark, URL_Review, User_FK)\r\n" + 
    			"VALUES ('Poor', 'Lame', 'youtube.com/9', 9);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql36);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql37 = "INSERT INTO Review (Score, Remark, URL_Review, User_FK)\r\n" + 
    			"VALUES ('Poor', 'Super club blows', 'youtube.com/1', 8);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql37);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql38 = "INSERT INTO Review (Score, Remark, URL_Review, User_FK)\r\n" + 
    			"VALUES ('Excellent', 'Lol', 'youtube.com/3', 3);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql38);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql39 = "INSERT INTO Review (Score, Remark, URL_Review, User_FK)\r\n" + 
    			"VALUES ('Poor', 'Boo', 'youtube.com/1', 7);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql39);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql40 = "INSERT INTO Videos (Descript, upload_date, title, tags, url, comedian_ID)\r\n" + 
    			"VALUES ('Youtube', '1999/02/22', 'Things to Do', 'fun,exciting,cool', 'www.youtube.com/10', 1);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql40);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql41 = "INSERT INTO Videos (Descript, upload_date, title, tags, url, comedian_ID)\r\n" + 
    			"VALUES ('Dope video', '1999/05/22', 'Shoving weird stuff up my nose for comedy', 'dope', 'www.youtube.com/1', 2);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql41);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql42 = "INSERT INTO Videos (Descript, upload_date, title, tags, url, comedian_ID)\r\n" + 
    			"VALUES ('Joke I groom my dog', '2008/05/02', 'Dog grooming', 'tough,yuck', 'www.youtube.com/2', 3);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql42);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql43 = "INSERT INTO Videos (Descript, upload_date, title, tags, url, comedian_ID)\r\n" + 
    			"VALUES ('Joke about eating boogers', '2018/05/02', 'Booger Eatn', 'funny,ew', 'www.youtube.com/3', 4);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql43);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql44 = "INSERT INTO Videos (Descript, upload_date, title, tags, url, comedian_ID)\r\n" + 
    			"VALUES ('Jokes mostly pertaining to his tiny feet', '2008/02/13', 'comedy,okay', 'Dane Cook 2008 standup', 'www.youtube.com/4', 5);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql44);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql45 = "INSERT INTO Videos (Descript, upload_date, title, tags, url, comedian_ID)\r\n" + 
    			"VALUES ('Joke about the time she worked a subway', '2020/05/02', 'interesting,cool', 'Amy Shumer being not funny',  'www.youtube.com/5', 6);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql45);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql46 = "INSERT INTO Videos (Descript, upload_date, title, tags, url, comedian_ID)\r\n" + 
    			"VALUES ('How people react to playing whats knew pussy cat in a diner', '2015/12/02', 'fun', 'John Mulaney, whats new pussycat',  'youtube.com/6', 7);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql46);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql47 = "INSERT INTO Videos (Descript, upload_date, title, tags, url, comedian_ID)\r\n" + 
    			"VALUES ('Pete Davidson talking about being lazy and doing drugs', '2008/05/02', 'exciting', 'Pete Davidson, lol moments', 'www.youtube.com/7', 8);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql47);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql48 = "INSERT INTO Videos (Descript, upload_date, title, tags, url, comedian_ID)\r\n" + 
    			"VALUES ('Rodney Dangerfield standup from 1975', '2005/04/03', 'interesting', 'Rodney Dangerfield, chill out babe, its a party', 'www.youtube.com/8', 9);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql48);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql49 = "INSERT INTO Videos (Descript, upload_date, title, tags, url, comedian_ID)\r\n" + 
    			"VALUES ('George Catan talking about his unpredictable strength', '2018/08/23', 'what', 'George Catan is strong', 'www.youtube.com/9', 10);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql49);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql50 = "INSERT INTO Favorite_Comedians (email, comedianID)\r\n" + 
    			"VALUES ('Craig@gmail.com', 1);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql50);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql51 = "INSERT INTO Favorite_Comedians (email, comedianID)\r\n" + 
    			"VALUES ('Jim@gmail.com', 2);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql51);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql52 = "INSERT INTO Favorite_Comedians (email, comedianID)\r\n" + 
    			"VALUES ('Tim@gmail.com', 3);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql52);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql53 = "INSERT INTO Favorite_Comedians (email, comedianID)\r\n" + 
    			"VALUES ('Justin@gmail.com', 4);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql53);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql54 = "INSERT INTO Favorite_Comedians (email, comedianID)\r\n" + 
    			"VALUES ('Lang@gmail.com', 5);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql54);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql55 = "INSERT INTO Favorite_Comedians (email, comedianID)\r\n" + 
    			"VALUES ('Gang@gmail.com', 6);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql55);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql56 = "INSERT INTO Favorite_Comedians (email, comedianID)\r\n" + 
    			"VALUES ('Rick@gmail.com', 7);\r\n";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql56);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql57 = "INSERT INTO Favorite_Comedians (email, comedianID)\r\n" + 
    			"VALUES ('Zak@gmail.com', 8);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql57);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql58 = "INSERT INTO Favorite_Comedians (email, comedianID)\r\n" + 
    			"VALUES ('Max@gmail.com', 9);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql58);
    	resultSet = preparedStatement.executeUpdate();
    	
    	String sql59 = "INSERT INTO Favorite_Comedians (email, comedianID)\r\n" + 
    			"VALUES ('Facts@gmail.com', 10);";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql59);
    	resultSet = preparedStatement.executeUpdate();

    	String foreignKeySql2 = "SET FOREIGN_KEY_CHECKS=1";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(foreignKeySql2);
    	resultSet = preparedStatement.executeUpdate();
    	
    	preparedStatement.close();
    }
     
    public boolean delete(String email) throws SQLException {
    	PrintStream out = System.out;
        String sql = "DELETE FROM users WHERE username = ?";        
        connect_func();
         
        out.println(email);
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        out.println(rowDeleted);
        preparedStatement.close();
//        disconnect();
        return rowDeleted;     
    }
     
    public boolean update(Users user, String refUser) throws SQLException {
        String sql = "UPDATE users SET Username = ?,First_name = ?,Last_name = ?,Age =? WHERE Username = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, user.username);
        preparedStatement.setString(2, user.first_name);
        preparedStatement.setString(3, user.last_name);
        preparedStatement.setString(4, user.age);
        preparedStatement.setString(5, refUser);
       // preparedStatement.setInt(6, user.id);
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowUpdated;     
    }
	
    public Users getPeople(String email) throws SQLException {
    	Users users = null;
        String sql = "SELECT * FROM users WHERE Username = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	int favorite = resultSet.getInt("favorites");
            String username = resultSet.getString("username");
            String gender = resultSet.getString("gender");
            String password = resultSet.getString("pass");
            String first_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");
            String age = resultSet.getString("age");
             
            users = new Users(username, age, first_name, last_name, password, gender, favorite);
        
        }
         
        resultSet.close();
        statement.close();
        return users;
    }
    
	public int getComedianID(HttpServletRequest request, HttpServletResponse response, String first, String last)
    		throws SQLException, IOException, ServletException{
    	int id = 0;
    	connect_func();
    	PrintStream out = System.out;
    	String returnSQL = "SELECT ID FROM comedian WHERE first_name = ? AND last_name = ?"; 
    	preparedStatement = (PreparedStatement) connect.prepareStatement(returnSQL);
        preparedStatement.setString(1, first);
        preparedStatement.setString(2, last);
               
        ResultSet resultSet = preparedStatement.executeQuery();
        
    	if(resultSet.next()) {
    		id = resultSet.getInt("ID");
    		out.println("the ID WE FOUND WAS: " + id);
    	}
    	resultSet.close();
        
        return id;
        
		}
	
	public int getComedianIDFromVideo(String url)
    		throws SQLException, IOException, ServletException{
    	int id = 0;
    	connect_func();
    	PrintStream out = System.out;
    	String returnSQL = "SELECT Comedian_ID FROM videos WHERE URL = ?"; 
    	preparedStatement = (PreparedStatement) connect.prepareStatement(returnSQL);
        preparedStatement.setString(1, url);
               
        ResultSet resultSet = preparedStatement.executeQuery();
        
    	if(resultSet.next()) {
    		id = resultSet.getInt("ID");
    		out.println("the ID WE FOUND WAS: " + id);
    	}
    	resultSet.close();
        
        return id;        
		}
	
	
    public List<Videos> findComVids(String com) throws SQLException{
    	PrintStream out = System.out;
    	List<Videos> listVideos = new ArrayList<Videos>();
    	String sql = "SELECT videos.*, comedian.* FROM videos, comedian WHERE comedian_id = ID AND First_name = ? AND Last_name =?";
    	
    	String tokens[] = com.split(" ");
    	if(tokens.length != 2) {throw new IllegalArgumentException();}
    	String first = tokens[0];
    	String last = tokens[1];
    			
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, first);
        preparedStatement.setString(2, last);
        ResultSet resultSet = preparedStatement.executeQuery(); 
        
        int i = 0;
        while(i < 2) {
        	resultSet.next();
            String URL = resultSet.getString("URL");
            out.println(URL);
            Videos video = new Videos(URL);
            listVideos.add(video);            
            out.println(i);
            i++;           
        }
        
        out.println("WE MADE IT");
        resultSet.close();
        return listVideos;
    }
}
