package modelo;

import java.io.InputStream;

public class Carrito {
   private int IdUsuario, IdProducto, Cantidad;
   private String Nombre, Descripcion;
   private float Precio;
   
   public Carrito(int idusuario, int idproducto, int cantidad){
       this.IdUsuario = idusuario;
       this.IdProducto = idproducto;
       this.Cantidad = cantidad;
   }
   
   public Carrito(int idusuario, int idproducto, int cantidad, String nombre, String descripcion, float precio){
       this.IdUsuario = idusuario;
       this.IdProducto = idproducto;
       this.Cantidad = cantidad;
       this.Nombre = nombre;
       this.Descripcion = descripcion;
       this.Precio = precio;
   }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }
}
