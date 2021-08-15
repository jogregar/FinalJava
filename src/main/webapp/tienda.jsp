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
            <div class="container">
                <div class="row">
                    <% 
                        String bus = "";
                        if (request.getParameter("buscar") != null) {
                            bus = request.getParameter("buscar");
                        }   
                        List<Producto> resultado = null;
                        ProductoDAO producto = new ProductoDAO();
                        resultado = producto.ListarProductos(bus);
                        for (int i=0; i<resultado.size(); i++){
                    %> 

                    <div class="col-sm-3 m-auto">
                        <div class="card mt-4">
                            <a href="ControladorProductos?idP=<%= resultado.get(i).getIdproducto()%>&accion=solicitar" class="l-card">
                                <input type="hidden" name="id" value=<%= resultado.get(i).getIdproducto()%>>
                                <img src="ControladorImagenes?id=<%= resultado.get(i).getIdproducto()%>" class="card-img-top" alt="...">
                                <div class="card-body text-justify">
                                    <h5 class="card-title text-danger"><%= resultado.get(i).getNombre()%></h5>
                                    <p class="card-text t-articulo"><%= resultado.get(i).getDescripcion()%></p>
                                </div>
                            </a>
                                <form action="ControladorCarrito" method="post">
                                <div class="container-fluid d-flex mb-2 justify-content-between">
                                    <input type="hidden" name="idU" value=<%= idusuario%>>
                                    <input type="hidden" name="idP" value=<%= resultado.get(i).getIdproducto()%>>
                                    <input type="hidden" name="accion" value="incluir">
                                    <div class="d-flex t-precio">
                                       <label class="t-precio">Cantidad</label>
                                        <input class="form-control t-precio ms-2 ancho" type="number" name="cantidad" value="1" min="1">
                                    </div>
                                    <p class="card-text t-precio text-end">$<%= formato.format(resultado.get(i).getPrecio())%></p>
                                </div>
                                <div class="text-center mb-2">
                                    <button type="submit" class="btn btn-primary text-center mb-2 letra">Agregar  <i class="fas fa-cart-plus"></i></button>
        <!--                            <a href="ControladorCarrito?idU=<%= idusuario%>&idP=<%= resultado.get(i).getIdproducto()%>&cantidad&accion=incluir" class="btn btn-primary letra">Agregar  <i class="fas fa-cart-plus"></i></a>
        -->                     </div>
                            </form>
                        </div>
                    </div>
                    <%}%>            
                </div>
            </div>
        <%}%>  
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>