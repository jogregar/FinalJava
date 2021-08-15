<%@page import = "java.util.Properties"%>
<%@page import = "java.io.*,java.util.*,javax.mail.*"%>
<%@page import = "javax.mail.internet.*,javax.activation.*"%>
<%@page import = "javax.servlet.http.*,javax.servlet.*" %>

<%
   String result;
   
   // Recipient's email ID needs to be mentioned.
   String to = "jogregar@gmail.com";

   // Sender's email ID needs to be mentioned
   String from = "jogregar22";

   // Assuming you are sending email from localhost
   String host = "localhost";

   // Get system properties object
   //Properties properties = System.getProperties();
   Properties properties = new Properties();
   // Setup mail server
   properties.put("mail.smtp.host", "smtp.gmail.com");
   //properties.setProperty("mail.smtp.host", "smtp.gmail.com");
   properties.setProperty("mail.smtp.starttls.enable", "true");
   properties.setProperty("mail.smtp.port", "587");
   properties.setProperty("mail smtp auth", "true");

   // Get the default Session object.
   Session mailSession = Session.getDefaultInstance(properties,null);

   try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(mailSession);
      
      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));
      
      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO,
                               new InternetAddress(to));
      // Set Subject: header field
      message.setSubject("prueba");
      
      // Now set the actual message
//      message.setText("<h1>Esto es un mensaje de prueba</h1>");
      message.setContent("<div align='justify'><h3>Jg Computer le da la bienvenida a nuestra tienda en linea. "
      + "Para activar su registro por favor haga clic en el siguiente enlace:</h3></div> "
      + "<div align='center'><h1><a href='http://localhost:8080/Proyecto/login.jsp'>Para Activar Su Registro Haga Clic Aqui</a></div>",
                            "text/html" );                         

// Send message
      Transport transporte = mailSession.getTransport("smtp");
      transporte.connect(from,"Ovgb0116");
      transporte.sendMessage(message, message.getAllRecipients());
      result = "Sent message successfully....";
   } catch (MessagingException mex) {
      mex.printStackTrace();
      result = "Error: unable to send message....";
   }
%>

<html>
   <head>
      <title>Send Email using JSP</title>
   </head>
   
   <body>
      <center>
         <h1>Send Email using JSP</h1>
      </center>
      
      <p align = "center">
         <% 
            out.println("Result: " + result + "\n");
         %>
      </p>
   </body>
</html>
