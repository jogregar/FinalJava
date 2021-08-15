package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {
    Connection conexion;
    
    public UsuariosDAO() throws ClassNotFoundException{
        Conexion con = new Conexion();
        conexion = con.getConnection();
    }
    
    public List<Usuario> ListarUsuario(int idusuario) throws Exception {
        PreparedStatement ps;
        ResultSet rs;
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios where idusuario=?";
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, idusuario);
        rs = ps.executeQuery();
        while(rs.next()){
            int idusu = rs.getInt("idusuario");
            String ape = rs.getString("apellido");
            String nom = rs.getString("nombre");
            String ema = rs.getString("email");
            String dir = rs.getString("direccion");
            String pas = rs.getString("password");
            String tel = rs.getString("telefono");
            int act = rs.getInt("activo");
            Usuario usuario = new Usuario(idusu, nom, ape, ema, tel, dir, pas, act);
            lista.add(usuario);
        }
        ps.close();
        conexion.close();
        return lista;
    }
    
    public Usuario mostrarUsuario(int _id) throws Exception{
        PreparedStatement ps;
        ResultSet rs;
        Usuario usuario = null;
        try{
            ps = conexion.prepareStatement("SELECT * FROM usuarios WHERE idusuario = ?");
            ps.setInt(1, _id);
            rs = ps.executeQuery();
            if (rs.next()){
                int idusu = rs.getInt("idusuario");
                String ape = rs.getString("apellido");
                String nom = rs.getString("nombre");
                String ema = rs.getString("email");
                String dir = rs.getString("direccion");
                String pas = rs.getString("password");
                String tel = rs.getString("telefono");
                int act = rs.getInt("activo");
                usuario = new Usuario(idusu, nom, ape, ema, tel, dir, pas, act);
            }else {
                throw new Exception("Id No Registrado");
            }
            return usuario;
        }catch(SQLException err) {
            System.out.println(err.toString());
        }  
        return null;
    }
    
    public boolean InsertarUsuario(Usuario usuario){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("INSERT INTO usuarios(nombre, apellido, direccion, email, "
                    + "telefono, password, activo) VALUES (?, ?, ?, ?, ?, sha(?), ?)");
            ps.setString(1,usuario.getNombre());
            ps.setString(2,usuario.getApellido());
            ps.setString(3,usuario.getDireccion());
            ps.setString(4,usuario.getEmail());
            ps.setString(5,usuario.getTelefono());
            ps.setString(6,usuario.getPassword());
            ps.setInt(7,usuario.getActivo());
            return ps.execute();
        }catch(SQLException err){
            System.out.println(err.toString());
            return false;
        }        
    }
    
    public boolean ActualizarUsuario(Usuario usuario){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("UPDATE usuario SET nombre=?, apellido=?, direccion=?, email=?, "
                    + "telefono=? WHERE idusuario=?");
            ps.setString(1,usuario.getNombre());
            ps.setString(2,usuario.getApellido());
            ps.setString(3,usuario.getDireccion());
            ps.setString(4,usuario.getEmail());
            ps.setString(5,usuario.getTelefono());
            ps.setInt(6,usuario.getIdUsuario());
            ps.execute();
            return true;
        }catch(SQLException err){
            System.out.println(err.toString());
            return false;
        }
    }
    
    public boolean ActualizarPassword(String email, String password){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("UPDATE usuarios SET password=sha(?) WHERE email=?");
            ps.setString(1,password);
            ps.setString(2,email);
            ps.execute();
            return true;
        }catch(SQLException err){
            System.out.println(err.toString());
            return false;
        }
    }

    public boolean ActivarCuenta(String email){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("UPDATE usuarios SET activo=1 WHERE email=?");
            ps.setString(1,email);
            ps.execute();
            return true;
        }catch(SQLException err){
            System.out.println(err.toString());
            return false;
        }
    }
    
     public boolean EliminarUsuario(int idusu){
        PreparedStatement ps;
        try{
            ps = conexion.prepareStatement("DELETE FROM usuario WHERE idusuario=?");
            ps.setInt(1,idusu);
            ps.execute();
            return true;
        }catch(SQLException err){
            System.out.println(err.toString());
            return false;
        }
    }
     
     public Usuario ValidarUsuario(String ema, String pass, int activo) throws Exception {
        PreparedStatement ps;
        ResultSet rs;
        Usuario usuario = null;
        try{
            String sql = "SELECT * FROM usuarios WHERE email = ?";
            if (pass != ""){
                sql += " and password = sha(?)";
            }
            ps = conexion.prepareStatement(sql);
            ps.setString(1, ema);
            if (pass != "") {
                ps.setString(2, pass);
            }
            rs = ps.executeQuery();
            if (rs.next()){
                int idusu = rs.getInt("idusuario");
                String ape = rs.getString("apellido");
                String nom = rs.getString("nombre");
                int act = rs.getInt("activo");
                usuario = new Usuario(idusu, nom, ape, act);
            }else {
                return null;
            }
            return usuario;
        }catch(SQLException err) {
            System.out.println(err.toString());
        }  
        return null;
    }
}
