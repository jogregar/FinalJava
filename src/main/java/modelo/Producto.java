package modelo;

import java.io.InputStream;

public class Producto {
   private int Idproducto;
   private String Nombre, Descripcion;
   private Float Precio;
   private InputStream Foto;

   public Producto(int idproducto, String nombre, String descripcion, Float precio, InputStream foto){
       this.Idproducto = idproducto;
       this.Nombre = nombre;
       this.Descripcion = descripcion;
       this.Precio = precio;
       this.Foto = foto;
   }

    public int getIdproducto() {
        return Idproducto;
    }

    public void setIdproducto(int Idproducto) {
        this.Idproducto = Idproducto;
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

    public void setPrecio(Float Precio) {
        this.Precio = Precio;
    }

    public InputStream getFoto() {
        return Foto;
    }

    public void setFoto(InputStream Foto) {
        this.Foto = Foto;
    }
}
