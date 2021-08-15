<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Tienda Online Login</title>
	<link rel="icon" href="img/Icono.png">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
	<link rel="stylesheet" href="css/estilos.css">
    </head>
<body>
    <div class="container-fluid m-auto header align-items-center justify-content-around">
        <div class="row contenido-header">
            <div class="col-md-7 m-auto opacidad ">
                <form action="ControladorUsuarios" method="post">
                    <h1>Registro de Usuario</h1>
                    <input type="hidden" name="accion" value="registro">
                    <div class="row justify-content-around">
                        <div class="col-md-6 mb-3">
                            <input type="text" class="form-control letra" placeholder="Nombres" name="nombres" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <input type="text" class="form-control letra" placeholder="Apellidos" name="apellidos" required>
                        </div>
                    </div>
                    <div class="row justify-content-around">
                        <div class="col-md-6 mb-3">
                            <input type="email" class="form-control letra" placeholder="Email" name="email" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <input type="text" class="form-control letra" placeholder="Teléfono" name="telefono" required>
                        </div>
                    </div>
                    <div class="col-md-12 mb-3">
                        <input type="text" class="form-control letra" placeholder="Dirección" name="direccion" required>
                    </div>
                    <div class="row justify-content-around">
                        <div class="col-md-6 mb-3">
                            <input type="password" class="form-control letra" placeholder="Contraseña" name="contrasena" id="contrasena1" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <input type="password" class="form-control letra" placeholder="Repita Contraseña" id="contrasena2" required>
                        </div>
                    </div>
                    <div class="col-md-12 pt-5">
                        <button type="submit" class="btn btn-primary letra">Registrarse</button>
                    </div>
                </form>
            </div>   
        </div>
    </div>
    
    <script src="js/app.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <% 
        String mensaje="", icono="", titulo="";
        if (request.getAttribute("Mensaje") != null) {
            mensaje = (String)request.getAttribute("Mensaje");
            icono = (String)request.getAttribute("Icono");
            titulo = (String)request.getAttribute("Titulo");
        %>
            <script>
                alerta4("<%= mensaje %>", "<%= icono %>", "<%= titulo %>");
            </script>
        <%}%>    
</body>
    
</html>
