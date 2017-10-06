package main.service;

import javax.mail.MessagingException;

public interface EmailService  {

    public void sendEmail(String to, String title, String content) ;

}
