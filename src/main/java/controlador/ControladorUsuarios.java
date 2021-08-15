package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.ProcessBuilder.Redirect.from;
import static java.lang.System.out;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Usuario;
import modelo.UsuariosDAO;

@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/ControladorUsuarios"})
public class ControladorUsuarios extends HttpServlet {
    
    RequestDispatcher distpacher = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try ( PrintWriter out = response.getWriter()) {
          
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operacion = request.getParameter("accion");
        if (!request.getParameterNames().hasMoreElements()) {
            distpacher = request.getRequestDispatcher("login.jsp");
            distpacher.forward(request, response);
        } else {
            Usuario usuario = null;
            UsuariosDAO usuariodao;
            String pass = request.getParameter("contrasena");
            switch (operacion){
                case "login":
                    String login = request.getParameter("email");
                    try {
                        Usuario resultado = null;
                        usuariodao = new UsuariosDAO();
                        resultado = usuariodao.ValidarUsuario(login, pass, 0);
                        if (resultado != null ){
                            if (resultado.getActivo() == 1) {
                                HttpSession sesion = request.getSession();
                                sesion.setAttribute("id", resultado.getIdUsuario());
                                sesion.setAttribute("Usuario", resultado.getNombre() + " " + resultado.getApellido());
                                response.sendRedirect("tienda.jsp");
                            }else {
                                request.setAttribute("Mensaje", "Usuario No Ha Confirmado Su Email");
                                request.setAttribute("Icono", "error");
                                request.setAttribute("Titulo", "Error");
                                distpacher = request.getRequestDispatcher("login.jsp");
                                distpacher.forward(request, response);
                            }

                        }else {
                            request.setAttribute("Mensaje", "Usuario o Password Incorrecto");
                            request.setAttribute("Icono", "error");
                            request.setAttribute("Titulo", "Error");
                            distpacher = request.getRequestDispatcher("login.jsp");
                            distpacher.forward(request, response);
    //                        response.sendRedirect("login.jsp");
                        }
                    }catch(Exception err) {
                        out.print("No hay Conexion con la Base de Datos. Intente mas tarde");
                    }  
                    break;
                case "registro":
                    String nom = request.getParameter("nombres");
                    String ape = request.getParameter("apellidos");
                    String ema = request.getParameter("email");
                    String tel = request.getParameter("telefono");
                    String dir = request.getParameter("direccion");

                    try {    
                        usuariodao = new UsuariosDAO();
                        usuario = usuariodao.ValidarUsuario(ema, "", 0);
                        if (usuario != null ){
                            request.setAttribute("Mensaje", "Email Ya Registrado");
                            request.setAttribute("Icono", "error");
                            request.setAttribute("Titulo", "Error");
                            distpacher = request.getRequestDispatcher("registro.jsp");
                            distpacher.forward(request, response);
                        }else {
                            usuario = new Usuario(0, nom, ape, ema, tel, dir, pass, 0);    
                            usuariodao.InsertarUsuario(usuario);
                            String MensajeEmail = "<div align='justify'><h3>Jg Computer le da la bienvenida a nuestra tienda en linea. "
                                    + "Para activar su registro por favor haga clic en el siguiente enlace:</h3></div>"
                                    + "<div align='center'><h1><a href='http://localhost:8080/Proyecto/ControladorUsuarios?email="
                                    + ema + "&accion=activar'>Para Activar Su Registro Haga Clic Aqui</a></div>";

                            getServerEnviarEmail(MensajeEmail, ema);

                            request.setAttribute("Mensaje", "Usuario Registrado Con Exito. Por Favor Reviza Tu Correo y Activa Tu Cuenta");
                            request.setAttribute("Icono", "success");
                            request.setAttribute("Titulo", "Bien");
                            distpacher = request.getRequestDispatcher("login.jsp");
                            distpacher.forward(request, response);
                        }
                    }catch(Exception err) {
                        out.print("No hay Conexion con la Base de Datos. Intente mas tarde");
                    }  
                    break; 
                case "activar":
                    try {    
                        usuariodao = new UsuariosDAO();
                        usuariodao.ActivarCuenta(request.getParameter("email"));
                        request.setAttribute("Mensaje", "Su Email Ha Sido Verificado. Ya Puedes Ingresar");
                        request.setAttribute("Icono", "success");
                        request.setAttribute("Titulo", "Bien");
                        distpacher = request.getRequestDispatcher("login.jsp");
                        distpacher.forward(request, response);
                    }catch(Exception err) {
                        out.print("No hay Conexion con la Base de Datos. Intente mas tarde");
                    }  
                    break;
                case "resetclave":
                    ema = request.getParameter("email");
                    try {    
                        usuariodao = new UsuariosDAO();
                        if (usuariodao.ValidarUsuario(ema, "", 0)!=null) {
                            String MensajeEmail = "<div align='justify'><h3>Jg Computer Le Informa Que Usted Ha Solicitado Restablecer Su Clave. "
                            + "Para Restablecerla Por Favor Haga Clic En El Siguiente Enlace:</h3></div>"
                            + "<div align='center'><h1><a href='http://localhost:8080/Proyecto/ControladorUsuarios?email="
                            + ema + "&accion=reset'" + ">Haga Clic Aqui Para Restabler Su Clave</a></div>";
                            getServerEnviarEmail(MensajeEmail, ema);
                            request.setAttribute("Mensaje", "Se Le Ha Enviado Un Email Para Restablecer Su Clave");
                            request.setAttribute("Icono", "success");
                            request.setAttribute("Titulo", "Bien");
                            distpacher = request.getRequestDispatcher("login.jsp");
                            distpacher.forward(request, response);
                        } else {
                            request.setAttribute("Mensaje", "Email No Registrado. Por Favor Verifiquelo");
                            request.setAttribute("Icono", "error");
                            request.setAttribute("Titulo", "Error");
                            distpacher = request.getRequestDispatcher("login.jsp");
                            distpacher.forward(request, response);
                        }                            
                    }catch(Exception err) {
                        out.print("No hay Conexion con la Base de Datos. Intente mas tarde");
                    } 
                    break;
                case "reset":
                    ema = request.getParameter("email");
                    try {    
                        usuariodao = new UsuariosDAO();
                        if (usuariodao.ValidarUsuario(ema, "", 0)!=null) {
                            request.setAttribute("Email", ema);
                            distpacher = request.getRequestDispatcher("resetclave.jsp");
                            distpacher.forward(request, response);
                        } else {
                            request.setAttribute("Mensaje", "Ha Ocurrido Un Error Con Este Email Por Favor Verifiquelo He Intente De Nuevo");
                            request.setAttribute("Icono", "error");
                            request.setAttribute("Titulo", "Error");
                            distpacher = request.getRequestDispatcher("login.jsp");
                            distpacher.forward(request, response);
                        }
                            
                    }catch(Exception err) {
                            out.print("No hay Conexion con la Base de Datos. Intente mas tarde");
                    } 
                    break;
                case "actualizarclave":
                    ema = request.getParameter("email");
                    pass = request.getParameter("contrasena1");
                    try {    
                        usuariodao = new UsuariosDAO();
                        usuariodao.ActualizarPassword(ema, pass);
                        request.setAttribute("Mensaje", "Su Contraseña Se Ha Cambiado Con Exito. Ya Puede Ingresar A La Tienda");
                        request.setAttribute("Icono", "success");
                        request.setAttribute("Titulo", "Bien");
                        distpacher = request.getRequestDispatcher("login.jsp");
                        distpacher.forward(request, response);    
                    }catch(Exception err) {
                            out.print("No hay Conexion con la Base de Datos. Intente mas tarde");
                    } 
                    break;
            }
        }   
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        
    }

    public void getServerEnviarEmail(String mensaje, String email) throws AddressException, MessagingException {
        String de = "jgcomputer22";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail smtp auth", "true");
        Session mailSession = Session.getDefaultInstance(properties,null); 
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(de));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("Jg Computer Activación");
        message.setContent(mensaje, "text/html" );
        Transport transporte = mailSession.getTransport("smtp");
        transporte.connect(de,"jg220575");
        transporte.sendMessage(message, message.getAllRecipients());
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
