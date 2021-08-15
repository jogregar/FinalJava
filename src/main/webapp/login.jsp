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
    <div class="container-fluid m-auto header">
        <div class="row contenido-header">
            <div class="col-md-3 m-auto p-5 opacidad position-relative">
                <form action="ControladorUsuarios" method="post">
                    <h1>Iniciar Sesión</h1>
                    <input type="hidden" name="accion" value="login">
                    <div class="mb-4 pt-5">
                        <input type="email" class="form-control letra" id="exampleInputEmail1" placeholder="Email" name="email" required>
                    </div>
                    <div class="input-group mb-4">
                        <input type="password" class="form-control letra" placeholder="Contraseña" name="contrasena" id="contrasena" required>
                        <span class="input-group-text" onclick="mostrarContrasena('contrasena', 'eye')"><i class="t-icono far fa-eye-slash" id="eye"></i></span>
                    </div>
                    <div class="d-grid pt-5">
                        <button type="submit" class="btn btn-primary letra">Iniciar Sesión</button>
                    </div>
                </form>
                <div class="loading ocultar" id="loading">
                    <img src="img/loading.gif" class="gif"/><br/>Espere, por favor...
                </div>
                <a class="position-absolute start-0 ps-5 pt-5 reset" id="resetclave">Olvido su clave</a>
                <a href="registro.jsp" class="position-absolute end-0 pe-5 pt-5">Registrese</a>
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
