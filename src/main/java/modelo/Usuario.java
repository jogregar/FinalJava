package modelo;

public class Usuario {
   private int IdUsuario, Activo;
   private String Nombre, Apellido, Email, Direccion, Telefono, Password;

   public Usuario (int idusuario, String nombre, String apellido, String email, String telefono, String direccion, String password, int activo) {
       this.IdUsuario = idusuario;
       this.Nombre = nombre;
       this.Apellido = apellido;
       this.Email = email;
       this.Telefono = telefono;
       this.Direccion = direccion;
       this.Password = password;
       this.Activo = activo;
   }
   
   public Usuario (int idusuario, String nombre, String apellido, int activo) {
       this.IdUsuario = idusuario;
       this.Nombre = nombre;
       this.Apellido = apellido;
       this.Activo = activo;
   }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public int getActivo() {
        return Activo;
    }

    public void setActivo(int Activo) {
        this.Activo = Activo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

   
}
