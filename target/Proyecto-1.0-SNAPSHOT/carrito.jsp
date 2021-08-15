<%@page import="java.text.DecimalFormat"%>
<%@page import="modelo.CarritoDAO"%>
<%@page import="modelo.Carrito"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="modelo.ProductoDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Producto"%>
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
       <%@include file="header.jsp" %> 

            <div class="container mt-5">
                <h2 class="ms-4" id="carrito">Carrito</h2>
                <div class="row">
                    <div class="col-sm-9">
                            <% 
                                String bus = "";
                                if (request.getParameter("buscar") != null) {
                                    bus = request.getParameter("buscar");
                                }    
                                List<Carrito> resultado = null;
                                CarritoDAO carrito2 = new CarritoDAO();
                                resultado = carrito2.ListarCarrito2(idusuario);
                                float totalcarrito = 0;
                                for (int i=0; i<resultado.size(); i++){
                                    totalcarrito += resultado.get(i).getCantidad() * resultado.get(i).getPrecio();
                            %>
                            <div class="container">
                                <div class="border bg-light mb-4">
                                    <div class="row justify-content-between">     
                                        <div class="col-md-3 align-items-center ms-4">
                                            <a href="ControladorProductos?idP=<%= resultado.get(i).getIdProducto()%>&accion=solicitar">
                                                <img src="ControladorImagenes?id=<%= resultado.get(i).getIdProducto()%>" class="img-fluid" alt="...">
                                            </a>
                                        </div>
                                        <div class="col-md-8 me-4 mt-5">
                                            <a href="ControladorProductos?idP=<%= resultado.get(i).getIdProducto()%>&accion=solicitar">
                                                <h5 class="text-danger mb-5"><%= resultado.get(i).getNombre() %></h5>
                                            </a>
                                            <p class="card-text t-precio text-center">$<%= formato.format(resultado.get(i).getPrecio())%> x Unidad</p>
                                            <form action="ControladorCarrito" method="post" name="formulario">
                                                <div class="container-fluid d-flex justify-content-between">
                                                    <input type="hidden" name="idU" id="idU" value=<%= idusuario%>>
                                                    <input type="hidden" name="idP" id="idP" value=<%= resultado.get(i).getIdProducto()%>>
                                                    <div class="d-flex">
                                                        <label class="t-precio">Cantidad</label>
                                                        <input class="form-control t-precio ms-2 ancho" min="1" type="number" id="cantidad" name="cantidad" value=<%= resultado.get(i).getCantidad()%>>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary" name="accion" value="eliminar">Eliminar</button>
                                                    <button type="submit" name="accion" value="modificar" id="modificar" class="modificar" hidden></button> 
                                                </div>
                                            </form>
                                        </div>
                                    </div>     
                                </div>
                            </div>                
                            <%}%>
                    </div> 
                    <div class="col-sm-3">
                        <div class="container-fluid bg-light border text-center d-grid gap-2 total">
                            <p class="card-text text-start mt-4">Total Articulos: <%= cant%></p>
                            <p class="card-text text-start">Total Carrito: $<%= formato.format(totalcarrito)%></p>
                            <a class="btn btn-primary letra mb-4 fluit">Pagar</a>
                        </div>
                    </div>
                </div>    
            </div>   
        <%}%>    
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="js/app.js"></script>
        
    </body>
</html>