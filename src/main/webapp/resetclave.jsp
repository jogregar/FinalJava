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
    <%
        String ema = (String)request.getAttribute("Email");
        if (request.getAttribute("Email") == null) {
            request.setAttribute("Mensaje", "Ha Ocurrido Un Error Con Este Email Por Favor Verifiquelo He Intente De Nuevo");
            request.setAttribute("Icono", "error");
            request.setAttribute("Titulo", "Error");
            response.sendRedirect("login.jsp");
        }else {
    %>
    <div class="container-fluid m-auto header">
        <div class="row contenido-header">
            <div class="col-md-3 m-auto p-5 opacidad position-relative">
                <form action="ControladorUsuarios" method="post">
                    <h1>Resetear Clave</h1>
                    <input type="hidden" name="accion" value="actualizarclave">
                    <input type="hidden" name="email" value="<%= ema%>">
                    <div class="pt-5 mb-4">
                        <input type="email" class="form-control letra" placeholder="Email" id="email" value="<%= ema%>" disabled>
                    </div>
                    <div class="input-group mb-4">
                        <input type="password" class="form-control letra" placeholder="Nueva Contraseña" id="contrasena1" name="contrasena1" required>
                        <span class="input-group-text" onclick="mostrarContrasena('contrasena1','eye1')"><i class="t-icono far fa-eye-slash" id="eye1"></i></span>
                    </div>
                    <div class="input-group mb-4">
                        <input type="password" class="form-control letra" placeholder="Repita Contraseña" id="contrasena2" required>
                        <span class="input-group-text" onclick="mostrarContrasena('contrasena2','eye2')"><i class="t-icono far fa-eye-slash" id="eye2"></i></span>
                    </div>
                    <div class="d-grid pt-5">
                        <button type="submit" class="btn btn-primary letra">Aceptar Cambios</button>
                    </div>
                </form>
            </div>   
        </div>
    </div>
    <%}%>
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
