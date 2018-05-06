package com.lab6_zadanie1;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: MDB02
 */
@MessageDriven(
    activationConfig = { 
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/jms/topic/lab6_zadanie1_topic"), 
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
    }, 
    mappedName = "lab6_zadanie1_topic")
public class MDB02 implements MessageListener {

    /**
     * Default constructor. 
     */
    public MDB02() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
      try {
        String textMessage = ((TextMessage)message).getText();
        System.out.println("MDB02: " + textMessage);
      } catch (JMSException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

}
