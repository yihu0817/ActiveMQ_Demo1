package com.warmtel.jms.demo1.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;

/**
 * JMS接收者
 */
public class ReceiveMessageListener implements MessageListener{
	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	public void onMessage(Message message) {
		 if (message instanceof TextMessage) {  
	            TextMessage text = (TextMessage) message;  
	            try {  
	               System.out.println("Received message:" + text.getText());  
	            } catch (JMSException e) {  
	                e.printStackTrace();  
	            }  
	        }  
		
	}

}
