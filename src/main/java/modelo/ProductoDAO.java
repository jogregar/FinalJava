package modelo;

import config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class ProductoDAO {
  Connection conexion;
    
    public ProductoDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        conexion = con.getConnection();
    }
    
    public List<Producto> ListarProductos(String nom) throws Exception {
        PreparedStatement ps;
        ResultSet rs;
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos ";
        String where = " WHERE 1=1";
        String nomb = nom;
        if (nomb != "") {
            where= where + " and nombre" + " LIKE " + "'%" + nomb + "%'" ;
        }
        sql = sql + where + " ORDER BY nombre";
        ps = conexion.prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt("idproducto");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            Float precio = rs.getFloat("precio");
            InputStream foto = rs.getBinaryStream("foto");
            Producto producto = new Producto(id, nombre, descripcion, precio, foto);
            lista.add(producto);
        }
        ps.close();
        conexion.close();
        return lista;
    }
    
    public void listarImg(int id, HttpServletResponse response) throws SQLException{
       PreparedStatement ps;
       ResultSet rs;
       InputStream input = null;
       OutputStream output = null;
       BufferedInputStream BuferInput = null;
       BufferedOutputStream BuferOutput = null;
       byte[] imagen = null;
       response.setContentType("imagen/*");
       try {
           output = response.getOutputStream();
           ps = conexion.prepareStatement("SELECT * FROM productos where idproducto=?");
           ps.setInt(1, id);
           rs = ps.executeQuery(); 
           
           while (rs.next()) {
                imagen = rs.getBytes("foto");
            }
            InputStream ima = new ByteArrayInputStream(imagen);

            int tamanoInput = ima.available();
            byte[] datosIMAGEN = new byte[tamanoInput];
            ima.read(datosIMAGEN, 0, tamanoInput);

            response.getOutputStream().write(datosIMAGEN);
            ima.close();
       }catch (Exception e){
           
       }
   } 
    
    public Producto mostrarProducto(int _id) throws Exception{
        PreparedStatement ps;
        ResultSet rs;
        Producto producto = null;
        try{
            ps = conexion.prepareStatement("SELECT * FROM productos WHERE idproducto = ?");
            ps.setInt(1,_id);
            rs = ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt("idproducto");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                Float precio = rs.getFloat("precio");
                InputStream foto = rs.getBinaryStream("foto");
                producto = new Producto(id, nombre, descripcion, precio, foto);
            }else {
                throw new Exception("Id No Registrado");
            }
            return producto;
        }catch(SQLException err) {
            System.out.println(err.toString());
        }  
        return null;
    }
    
    public boolean InsertarProducto(Producto producto){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("INSERT INTO productos(nombre, descripcion, precio, foto) VALUES (?, ?, ?, ?)");
            ps.setString(1,producto.getNombre());
            ps.setString(2,producto.getDescripcion());
            ps.setFloat(3,producto.getPrecio());
            ps.setBlob(4,producto.getFoto());
            ps.execute();
            return true;
        }catch(SQLException err){
            System.out.println(err.toString());
            return false;
        }        
    }
    
    public boolean ActualizarProducto(Producto producto){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("UPDATE productos SET nombre=?, descripcion=?, precio=?, foto=? WHERE id=?");
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3,producto.getPrecio());
            ps.setBlob(4,producto.getFoto());
            ps.setInt(5,producto.getIdproducto());
            ps.execute();
            return true;
        }catch(SQLException err){
            System.out.println(err.toString());
            return false;
        }
    }
    
     public boolean EliminarProducto(int _id){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("DELETE FROM productos WHERE id=?");
            ps.setInt(1,_id);
            ps.execute();
            return true;
        }catch(SQLException err){
            System.out.println(err.toString());
            return false;
        }
    }  
}
