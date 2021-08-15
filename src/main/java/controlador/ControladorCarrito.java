package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Carrito;
import modelo.CarritoDAO;

@WebServlet(name = "ControladorCarrito", urlPatterns = {"/ControladorCarrito"})
public class ControladorCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        try ( PrintWriter out = response.getWriter()) {
            out.println(request.getParameter("accion"));
            out.println(Integer.parseInt(request.getParameter("idU")));
            out.println(Integer.parseInt(request.getParameter("idP")));
            out.println(Integer.parseInt(request.getParameter("cantidad")));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String operacion = request.getParameter("accion");
        int idusu = Integer.parseInt(request.getParameter("idU"));
        int idpro = Integer.parseInt(request.getParameter("idP"));
        int cant = Integer.parseInt(request.getParameter("cantidad"));
        RequestDispatcher distpacher = null;
        switch (operacion){
            case "incluir":
                try {
                    Carrito carrito = new Carrito(idusu, idpro, cant);
                    CarritoDAO carritodao = new CarritoDAO();
                    carritodao.InsertarCarrito(carrito);
                    response.sendRedirect("tienda.jsp");
                } catch (ClassNotFoundException ex) {
                     Logger.getLogger(ControladorCarrito.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "modificar":
                try {
                    Carrito carrito = new Carrito(idusu, idpro, cant);
                    CarritoDAO carritodao = new CarritoDAO();
                    carritodao.ActualizarCarrito(carrito);
                    distpacher = request.getRequestDispatcher("carrito.jsp");
                    distpacher.forward(request, response);
                } catch (ClassNotFoundException ex) {
                     Logger.getLogger(ControladorCarrito.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "eliminar":
                try {
                    CarritoDAO carritodao = new CarritoDAO();             
                    carritodao.EliminarCarrito(idusu, idpro);
                    distpacher = request.getRequestDispatcher("carrito.jsp");
                    distpacher.forward(request, response);
                } catch (ClassNotFoundException ex) {
                     Logger.getLogger(ControladorCarrito.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        } 
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
