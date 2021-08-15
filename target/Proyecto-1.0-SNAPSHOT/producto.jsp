<%@page import="java.text.DecimalFormat"%>
<%@page import="modelo.Producto"%>
<%@page import="modelo.CarritoDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Carrito"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jg Computer</title>
        <link rel="icon" href="img/Icono.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>
        <%
            Producto resultado=(Producto)request.getAttribute("Datos");
        %>
        <%@include file="header.jsp" %> 
        
            <div class="container mt-5 bg-light">
                <div class="row border">
                    <div class="col-md-5 text-center">
                        <img src="ControladorImagenes?id=<%= resultado.getIdproducto()%>" class="img-fluid " alt="...">
                    </div>
                    <div class="col-md-5 m-auto">
                        <h5 class="text-danger mb-5"><%= resultado.getNombre() %></h5>
                        <p class="justificar mb-5"><%= resultado.getDescripcion() %></p> 
                        <form action="ControladorCarrito" method="post">
                            <div class="container-fluid d-flex mb-2 justify-content-between">
                                <input type="hidden" name="idU" value=<%= idusuario%>>
                                <input type="hidden" name="idP" value=<%= resultado.getIdproducto()%>>
                                <div class="d-flex t-precio">Cantidad
                                    <select class="form-select t-precio ms-1"  name="cantidad">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5">5</option>
                                        <option value="6">6</option>
                                    </select>
                                </div>
                                <p class="card-text t-precio text-end">$<%= formato.format(resultado.getPrecio()) %></p>
                            </div>
                            <div class="d-flex mt-5 justify-content-between">
                                <button type="submit" class="btn btn-primary text-center mb-2 letra" name="accion" value="incluir">Agregar  <i class="fas fa-cart-plus"></i></button>
                                <button type="submit" class="btn btn-primary text-center mb-2 letra" name="accion" value="comprar">Comprar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        <%}%>
        
    </body>
</html>
