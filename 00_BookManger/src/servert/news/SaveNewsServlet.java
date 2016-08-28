package servert.news;

import impl.NewDAOimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NewDAO;

import enetiy.News;

import tool.DateConvernt;

public class SaveNewsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		int  adminId =Integer.parseInt( request.getParameter("adminId"));
		String title = request.getParameter("title");
		String context = request.getParameter("context");
		String btdStr = request.getParameter("fbTime");
		Date fbTime  = DateConvernt.ConvertToDate(btdStr);
		News news = new News(adminId, title, context, fbTime);
		NewDAO dao = new NewDAOimpl();
		String msg = "新增新闻信息失败!";
	     String returnPath ="background/saveNews.jsp";
		    if (dao.save(news)) {
		    	msg = "新增新闻信息成功！";
		    	returnPath = "/FindAllNewsServlet?nowPage=1";
			}
		    request.setAttribute("msg", msg);
		    request.setAttribute("returnPath", returnPath);
			RequestDispatcher rd = request.getRequestDispatcher("background/msg.jsp");
			rd.forward(request, response);
		
		
		
	}

}
