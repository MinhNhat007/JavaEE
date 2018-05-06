package com.company;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: RegistrationBean
 */
@MessageDriven(
		activationConfig = { 
		    @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/lab6_zadanie2_queue"), 
		    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "lab6_zadanie2_queue")
public class RegistrationBean implements MessageListener {
  
  @EJB
  CompanyBean companyBean;
    /**
     * Default constructor. 
     */
    public RegistrationBean() {
        // TODO Auto-generated constructor stub
    }
	
    /**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
      try {
        TextMessage textMessage = (TextMessage) message;
        System.out.println("Received msg: " + textMessage.getText());
        companyBean.addCompany(textMessage.getText());
      } catch (JMSException e) {
        e.printStackTrace();
      }
        
    }

}
