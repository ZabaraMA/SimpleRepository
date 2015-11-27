package QuizLogic.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import QuizLogic.dao.TopicDao;

public class ViewTopics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewTopics() {
        super();
    }    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TopicDao topicDao = new TopicDao(); 
		//request.setAttribute("topics", topicDao.selectAll());
		//request.getRequestDispatcher("topics.jsp").forward(request, response);
		PrintWriter pw = response.getWriter();
		pw.println(getServletConfig().getInitParameter("header"));
		pw.flush();
		pw.close();
	}
	
}
