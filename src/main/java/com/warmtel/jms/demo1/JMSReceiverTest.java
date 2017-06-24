package com.warmtel.jms.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.warmtel.jms.demo1.consumer.JMSReceiver;

/**
 * 
 * @author viktor.zhou
 *
 * @time2017年6月24日
 * 
 * 自定义实现消息接收
 */
public class JMSReceiverTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "spring-jms.xml" });
		JMSReceiver jMSConsumer = (JMSReceiver) applicationContext.getBean("messageReceiver");
		System.out.println("初始化消息消费者");
		jMSConsumer.recive();
	}
}
