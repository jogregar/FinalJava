package modelo;

import config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class CarritoDAO {
   Connection conexion;
    
    public CarritoDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        conexion = con.getConnection();
    }
    
    public List<Carrito> ListarCarrito(int idusuario) throws Exception {
        PreparedStatement ps;
        ResultSet rs;
        List<Carrito> lista = new ArrayList<>();
        String sql = "SELECT * FROM carrito where idusuario=?";
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, idusuario);
        rs = ps.executeQuery();
        while(rs.next()){
            int idusu = rs.getInt("idusuario");
            int idpro = rs.getInt("idproducto");
            int can = rs.getInt("cantidad");
            Carrito carrito = new Carrito(idusu, idpro, can);
            lista.add(carrito);
        }
        ps.close();
        conexion.close();
        return lista;
    }
    
    public List<Carrito> ListarCarrito2(int idusuario) throws Exception {
        PreparedStatement ps;
        ResultSet rs;
        List<Carrito> lista = new ArrayList<>();
        String sql = "SELECT productos.idproducto, productos.nombre, productos.descripcion, "
                + "productos.precio, carrito.cantidad FROM productos JOIN carrito ON productos.idproducto = "
                + "carrito.idproducto JOIN usuarios on usuarios.idusuario = carrito.idusuario where "
                + "usuarios.idusuario = ?";     
//        String sql = "SELECT * FROM carrito where idusuario=?";
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, idusuario);
        rs = ps.executeQuery();
        while(rs.next()){
 //           int idusu = rs.getInt("idusuario");
            int idpro = rs.getInt("idproducto");
            String nompro = rs.getString("nombre");
            String desp = rs.getString("descripcion");
            float pre = rs.getFloat("precio");
            int can = rs.getInt("cantidad");
            Carrito carrito = new Carrito(idusuario, idpro, can, nompro, desp, pre);
            lista.add(carrito);
        }
        ps.close();
        conexion.close();
        return lista;
    }
    
    public Carrito mostrarCarrito(int _id) throws Exception{
        PreparedStatement ps;
        ResultSet rs;
        Carrito carrito = null;
        try{
            ps = conexion.prepareStatement("SELECT * FROM carrito WHERE id = ?");
            ps.setInt(1, _id);
            rs = ps.executeQuery();
            if (rs.next()){
                int idusu = rs.getInt("idusuario");
                int idpro = rs.getInt("idproducto");
                int can = rs.getInt("cantidad");
                carrito = new Carrito(idusu, idpro, can);
            }else {
                throw new Exception("Id No Registrado");
            }
            return carrito;
        }catch(SQLException err) {
            System.out.println(err.toString());
        }  
        return null;
    }
    
    public boolean InsertarCarrito(Carrito carrito){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("INSERT INTO carrito(idusuario, idproducto, cantidad) VALUES (?, ?, ?)");
            ps.setInt(1,carrito.getIdUsuario());
            ps.setInt(2,carrito.getIdProducto());
            ps.setInt(3,carrito.getCantidad());
            ps.execute();
            return true;
        }catch(SQLException err){
            System.out.println(err.toString());
            return false;
        }        
    }
    
    public boolean ActualizarCarrito(Carrito carrito){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("UPDATE carrito SET cantidad=? WHERE idusuario=? AND idproducto=?");
            ps.setInt(1, carrito.getCantidad());
            ps.setInt(2, carrito.getIdUsuario());
            ps.setInt(3, carrito.getIdProducto());
            ps.execute();
            return true;
        }catch(SQLException err){
            System.out.println(err.toString());
            return false;
        }
    }
    
     public boolean EliminarCarrito(int idusu, int idpro){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("DELETE FROM carrito WHERE idusuario=? AND idproducto=?");
            ps.setInt(1,idusu);
            ps.setInt(2,idpro);
            ps.execute();
            return true;
        }catch(SQLException err){
            System.out.println(err.toString());
            return false;
        }
    }  
}
