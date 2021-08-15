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
import modelo.Producto;
import modelo.ProductoDAO;

@WebServlet(name = "ControladorProductos", urlPatterns = {"/ControladorProductos"})
public class ControladorProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        try ( PrintWriter out = response.getWriter()) {
            
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        String operacion = request.getParameter("accion");
        RequestDispatcher distpacher = null;
        switch (operacion){
            case "solicitar":
                int idpro = Integer.parseInt(request.getParameter("idP"));
                Producto resultado = null;
                ProductoDAO productodao;
                try {
                    productodao = new ProductoDAO();
                    resultado = productodao.mostrarProducto(idpro);
                    request.setAttribute("Datos", resultado);
    /*                request.getRequestDispatcher("/Vistas/modificar3.jsp").forward(request, response);*/
                    distpacher = request.getRequestDispatcher("producto.jsp");
                    distpacher.forward(request, response);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControladorProductos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ControladorProductos.class.getName()).log(Level.SEVERE, null, ex);
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
