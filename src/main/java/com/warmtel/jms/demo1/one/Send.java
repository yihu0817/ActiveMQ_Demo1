package com.warmtel.jms.demo1.one;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

@WebServlet(name = "Send", urlPatterns = { "/Send" })
public class Send extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String sendmsg = new String(request.getParameter("message").getBytes("ISO8859-1"), "UTF-8"); // ����ǰ̨�������Ϣ
		String receiveurl = "http://localhost:8080/JMSProject/Receive"; // ����ʵ�ʽ���·������
		String title = "ʹ�� GET ��ȡ��Ϣ�����ߵ���Ϣ";
		try {
			// 1��ͳһ��Ϣ��������������
			// get the initial context
			InitialContext context = new InitialContext();

			// lookup the queue object
			Queue queue = (Queue) context.lookup("java:comp/env/queue/queue0");

			// lookup the queue connection factory
			QueueConnectionFactory conFactory = (QueueConnectionFactory) context
					.lookup("java:comp/env/queue/connectionFactory");
			// 2��ͨ�����ӽ�������
			// create a queue connection
			QueueConnection queConn = conFactory.createQueueConnection();

			// create a queue session
			QueueSession queSession = queConn.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);
			// 3�������� ��
			// create a queue sender
			QueueSender queSender = queSession.createSender(queue);
			queSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// create a simple message to say "Hello World"
			TextMessage message = queSession.createTextMessage(sendmsg);

			// send the message
			queSender.send(message);

			// print what we did
			// out.write("Message Sent: " + message.getText()) ;

			String docType = "<!DOCTYPE html> \n";
			out.write(docType + "<html>\n" + "<head><title>" + title + "</title></head>\n"
					+ "<body bgcolor=\"#f0f0f0\">\n" + "<h1 align=\"center\">" + title + "</h1>\n" + "<ul>\n"
					+ "  <li><b>���͵���ϢΪ:</b>��" + message.getText() + "\n" + "  <li><b>���������ϢURL:</b>" + "<a href=\""
					+ receiveurl + " \"> " + receiveurl + " </a>" + "\n" + "</ul>\n" + "</body></html>");

			// close the queue connection
			queConn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
