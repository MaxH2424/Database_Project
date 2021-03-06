import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller fo  r the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PeopleDAO peopleDAO;
    private String currUser;
    
    public void init() {
        peopleDAO = new PeopleDAO(); 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
            	insertPeople(request, response);
                break;
            case "/insertVideo":
            	insertVideo(request, response);
            	break;
            case "/goToVideoPg":
            	goToVideoPg(request, response);
            	break;
            case "/delete":
            	deletePeople(request, response);
                break;
            case "/login":
            	goToLoginScreen(request, response);
            	break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
            	updatePeople(request, response);
                break;
            case "/brand":
            	listBrandNew(request, response);
            	break;
            case "/searchInterface":
            	searchInterface(request, response);
            	break;
            case "/list":
            	listBrandNew(request, response);
            	break;
            case "/initialize":
            	peopleDAO.initializeDatabase();
            	listBrandNew(request, response);
            	break;
            case "/search":
            	comedianTagScreen(request, response);
            	break;
           
            case "/add":
            	break;
            case "/signOut":
            	System.out.println(currUser + " Has signed out successfully");
            	currUser = "";
            	goToLoginScreen(request, response);
            	break;
            	
            default:          	            	          	
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void listBrandNew(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
    	List<Users> listUsers = peopleDAO.listAllPeople();
    	request.setAttribute("listUsers", listUsers);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleList.jsp");
    	dispatcher.forward(request, response);
    }
    

    
    private void listFavorites(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException{
    	PrintStream out = System.out;
    	List<Comedians> favoritesList = peopleDAO.findFavorites(currUser);
    	out.println("Printng of UTD");
    	for(int i = 0; i < favoritesList.size(); i++) { // Output of urls
    		out.println(favoritesList.get(i).getfName() + " " + favoritesList.get(i).getlName());
    	}
    	out.println("End of UTD");
    	out.println("-------------");
    	request.setAttribute("FavoritesList", favoritesList);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("FavoritesList.jsp");
    	dispatcher.forward(request, response);
    }
    
    private void comedianTagScreen(HttpServletRequest request, HttpServletResponse response)
    		throws SQLException, IOException, ServletException{
    	RequestDispatcher rd = request.getRequestDispatcher("SearchComedian_Tag.jsp");
    	rd.forward(request, response);   	
    }
    
    private void searchInterface(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	PrintStream out = System.out;
    	String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean checker = peopleDAO.checkLogin(username, password);
        out.println(username);
        out.println(password);
        
        if (checker) {
        	currUser=username;
        	out.println(currUser + "has logged in successfully");
        	List<Users> listUsers = peopleDAO.listAllPeople();
            request.setAttribute("listUsers", listUsers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("SearchInterface.jsp");       
            dispatcher.forward(request, response);
        }
        else if(username.equals("root") && password.equals("pass1234")) {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("RootPage.jsp");       
            dispatcher.forward(request, response);
        }
        else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");       
            dispatcher.forward(request, response);
        }
        	
        
    }
 
    // to insert a people
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("InsertPeopleForm.jsp");
        dispatcher.forward(request, response);
    }
    
    private void goToLoginScreen(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException{
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
        dispatcher.forward(request, response);
    }
 
    // to present an update form to update an  existing Student
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String email = request.getParameter("user");
        Users existingPeople = peopleDAO.getPeople(email);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditPeopleForm.jsp");
        request.setAttribute("user", existingPeople);
        dispatcher.forward(request, response); // The forward() method works at server side, and It sends the same request and response objects to another servlet.
    }
 
    // after the data of a people are inserted, this method will be called to insert the new people into the DB
    // 
    private void insertPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String gender = request.getParameter("gender");
        String favorites = request.getParameter("favorite");
        String age = request.getParameter("age");
        
        int favorite = Integer.parseInt(favorites);
		
        
        Users newUser = new Users(username, age, first_name, last_name, password, gender,favorite);
        
        boolean checker = peopleDAO.checkUser(newUser);
        if (checker) {
        	RequestDispatcher rd = request.getRequestDispatcher("InsertPeopleForm.jsp");
        	rd.forward(request, response);
        }
        else {
            peopleDAO.insertPeopleQuery(newUser);
        	listBrandNew(request, response);
        }
         // The sendRedirect() method works at client side and sends a new request
        
    }
    
	@SuppressWarnings("deprecation")
	private void insertVideo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
        String video = request.getParameter("url");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String tags = request.getParameter("tags");
        String comedian = request.getParameter("comedian");
        
        String tokens[] = comedian.split(" ");
    	if(tokens.length != 2) {throw new IllegalArgumentException();}
    	String first = tokens[0];
    	String last = tokens[1];
    	
    	PeopleDAO comID = new PeopleDAO();
    	int id = comID.getComedianID(request, response, first, last);
    	
    	java.util.Date todayDate = new java.util.Date();
    	@SuppressWarnings("deprecation")
		int year = todayDate.getYear();
    	int month = todayDate.getMonth();
    	int day = todayDate.getDay();
    	
    	java.sql.Date postDate = new java.sql.Date(year, month, day);
    	
        
        Videos newVideo = new Videos(video, postDate, title, description, tags, id);
        boolean checker = peopleDAO.checkVideo(newVideo);
        if (checker) {
        	RequestDispatcher rd = request.getRequestDispatcher("InsertPeopleForm.jsp");
        	rd.forward(request, response);
        }
        else {
            peopleDAO.insertVideoQuery(newVideo);
        	listBrandNew(request, response);
        }
         // The sendRedirect() method works at client side and sends a new request
        
    }
	
	private void insertComment(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		PeopleDAO comID = new PeopleDAO();
		String rating = request.getParameter("selection");
		String comment = request.getParameter("comment");		
		String url = request.getParameter("td1");
		int id = comID.getComedianIDFromVideo(url);
		
		Comments newComment = new Comments(rating, comment, id, url);
		comID.insertCommentQuery(newComment);
		
		RequestDispatcher rd = request.getRequestDispatcher("Comment.jsp");
		rd.forward(request, response);
	}
    
    private void goToVideoPg(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	RequestDispatcher rd = request.getRequestDispatcher("InsertVideo.jsp");
    	rd.forward(request, response);    	
    }
 
    private void updatePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
    	String refUser = request.getParameter("refuser");
    	String username = request.getParameter("username");
        //int id = Integer.parseInt(request.getParameter("id"));
       
        //System.out.println(id);
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String age = request.getParameter("age");
        System.out.println(refUser);
        
        Users user = new Users(username, first_name, last_name, age);
        peopleDAO.update(user, refUser);
        response.sendRedirect("brand");
    }
 
    private void deletePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String email = request.getParameter("user");
        //People people = new People(id);
        peopleDAO.delete(email);
        response.sendRedirect("brand"); 
    }
    
    

}