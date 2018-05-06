package com.company;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CompanyServlet
 */
@WebServlet("/companyServlet.do")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String companyName = request.getParameter("companyName");
	  System.out.println("doPost method in servlet");
	  try {
      Context context = new InitialContext();
      Queue queue = (Queue) context.lookup("java:/jms/queue/lab6_zadanie2_queue");
      QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("ConnectionFactory");
      QueueConnection queueConnection = factory.createQueueConnection();
      QueueSession queueSession = queueConnection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
      TextMessage textMessage = queueSession.createTextMessage(companyName);
      QueueSender queueSender = queueSession.createSender(queue);
      queueSender.send(textMessage);
    } catch (NamingException | JMSException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	}

}
