

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
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
 * Servlet implementation class lab6Zadanie1Servlet
 */
@WebServlet("/lab6Zadanie1Servlet")
public class lab6Zadanie1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lab6Zadanie1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  System.out.println("doGet method in servlet");
	  try {
      Context context = new InitialContext();
      Topic topic = (Topic) context.lookup("java:jboss/jms/topic/lab6_zadanie1_topic");
      TopicConnectionFactory factory = (TopicConnectionFactory) context.lookup("ConnectionFactory");
      TopicConnection topicConnection = factory.createTopicConnection();
      TopicSession topicSession = topicConnection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
      TextMessage textMessage = topicSession.createTextMessage("Message from servlet");
      TopicPublisher topicPublisher = topicSession.createPublisher(topic);
      topicPublisher.send(textMessage);
    } catch (NamingException | JMSException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	  System.out.println("Exit doGet method in servlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
