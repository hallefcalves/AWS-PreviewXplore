package ec.ftt.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.dao.UserDao;
import ec.ftt.model.User;

/**
 * Servlet implementation class UserApi
 * 
 * CRUD - 
 * 
 */

@WebServlet("/user")
public class UserApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserApi() {
        super();
       
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("user-id");
		
	    if(userId != null) {
	    	long id = Long.valueOf(userId);
	    	
	    	UserDao userDao = new UserDao();
	    	
	        User user = userDao.getUserById(id);
	     	Gson gson = new Gson();
	    	response.getWriter().append(gson.toJson(user));
	    	
	    } else {
	    	
	    	UserDao userDao = new UserDao();
	    	
	    	List<User> users = userDao.getAllUser();
	        
	    	Gson gson = new Gson();

	    	response.getWriter().append(gson.toJson(users));
	    	/*
	    	 for (User u : users)
	    	 
	    		response.getWriter().append(u.toString());
	    	*/
	    } //if
		
		
	} //doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User u = new User(
				request.getParameter("user-id"),
				request.getParameter("user-name"),
				request.getParameter("user-email")
				);
		
		UserDao userDao = new UserDao();
		
		userDao.addUser(u);
		
		System.out.println(u);
		
		response.sendRedirect("FTT-WEB-091/index.html");
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json"); //mimeType - https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
		User u = new User(
				request.getParameter("user-id"),
				request.getParameter("user-name"),
				request.getParameter("user-email")
				);
		UserDao userDao = new UserDao();
		
		userDao.updateUser(u);
		
		System.out.println(u);
		
		response.sendRedirect("WEB-FTT-091/index.html");
		
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (request.getParameter("user-id") == null)
			 response.sendError(407, "Informe o ID do usuário a ser retornado!!!" );
		else {
		Long userId = Long.valueOf(request.getParameter("user-id"));
		
		
		
		UserDao ud = new UserDao();
		
		ud.deleteUser(userId);
		
		response.getWriter().append(request.getParameter("user-id") + " User removido");
		}
	}

}
