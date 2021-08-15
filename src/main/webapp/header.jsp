        <%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("Usuario") == null){
                response.sendRedirect("login.jsp");
            }else {
                DecimalFormat formato = new DecimalFormat("##,###,###.00"); 
                int idusuario = ((Integer)sesion.getAttribute("id")).intValue();
                int cant = 0;
                List<Carrito> carritoresultado = null;
                CarritoDAO carrito = new CarritoDAO();
                carritoresultado = carrito.ListarCarrito(idusuario);
                for (int i=0; i<carritoresultado.size(); i++){
                    cant += carritoresultado.get(i).getCantidad();
                }%>
                
                
                
            <nav class="navbar navbar-fixed-top navbar-dark bg-secondary top">
                <div class="container-fluid">
                    <a href="#"><img src="img/Logo2.png" class="logo" alt=""></a>
                    <div class="navbar-header">
                        <form class="form" action="tienda.jsp" method="post">
                            <div class="d-flex">
                                <input type="text" class="form-control letra me-2" placeholder="Buscar por Nombre" id="buscar" name="buscar" required>
                                <div>
                                    <button id="buscar" class="btn btn-primary me-2" type="submit" title="Buscar"> <span class="fas fa-search"></span></button>
                                </div>
                                <div>
                                    <a class="btn btn-primary" href="tienda.jsp" title="Ver Todos" ><i class="fas fa-users"></i></a>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div>
                        <i class="fas fa-user-circle me-2 text-light"> <%= sesion.getAttribute("Usuario")%></i>
                        <a class="barra text-light" href="carrito.jsp" title="Ver Carrito"><i class="fas fa-cart-plus"></i>
                            <div class="carrito"><%= cant%>

                            </div>
                        </a>
                        <a class="barra ms-4 text-light" href="logout.jsp" title="Salir"><i class="fas fa-sign-out-alt"></i></a>
                    </div>
                </div>
            </nav>
