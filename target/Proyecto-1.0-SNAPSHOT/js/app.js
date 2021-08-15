function mostrarContrasena(id,ojo){  
    if(document.getElementById(id).type == "password"){
        document.getElementById(id).type = "text";
        document.getElementById(ojo).classList.remove("fa-eye-slash");
        document.getElementById(ojo).classList.add("fa-eye");
    }else{
        document.getElementById(id).type = "password";
        document.getElementById(ojo).classList.remove("fa-eye");
        document.getElementById(ojo).classList.add("fa-eye-slash"); 
    }
}

if (document.getElementById("contrasena2")) {
    contra = document.getElementById("contrasena2");
    contra.addEventListener('change', () => {
        if (document.getElementById("contrasena1").value != contra.value) {
              alerta4("Las Contraseñas No Coinciden");
              document.getElementById("contrasena1").value = "";
              contra.value = "";
        }
    })
}

if (document.getElementById("carrito")) {
    carrito = document.getElementsByName("cantidad");
    boton = document.getElementsByClassName("modificar");
    for (a = 0; a <= carrito.length -1; a++) {
        carrito[a].setAttribute("data-idp",a);
    }
    
    for (a = 0; a <= carrito.length -1; a++) {
        carrito[a].addEventListener('change', () => {
            boton[document.activeElement.getAttribute("data-idp")].click();
        });
    }  
}

if (document.getElementById("resetclave") ){
    document.getElementById("resetclave").addEventListener('click', () => {
        if (document.getElementById("exampleInputEmail1").value != "") {
            document.getElementById("loading").classList.toggle("ocultar");
            window.location.href ="ControladorUsuarios?email=" + document.getElementById("exampleInputEmail1").value + "&accion=resetclave";
        } else {
            alerta4("Debe Ingresar Su Email", "error", "Error");
        }
        
    });
}

alerta = (id) => {
  console.log(id);
  Swal.fire({
    title: 'Estas Seguro?',
    text: "Esta Operación No Se Puede Deshacer",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Si, Eliminar!',
    cancelButtonText: 'No, Cancelar'

  }).then((result) => {
    if (result.isConfirmed) {
      window.location.href ="/borrar/"+id;
    }
  })
}

alerta2 = (mensaje) => {
  Swal.fire({
    title: mensaje,
    text: 'Correctamente',
    icon: 'success'
  })
}

alerta3 = (mensaje) =>  {
  const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: false,
    didOpen: (toast) => {
      toast.addEventListener('mouseenter', Swal.stopTimer)
      toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
  })

  Toast.fire({
    icon: 'success',
    title: mensaje + ' Correctamente'
  })
}

alerta4 = (mensaje, icono, titulo) => {
  Swal.fire({
    icon: icono,
    title:  titulo,
    text: mensaje,
  })
}
