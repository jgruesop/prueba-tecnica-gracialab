import Swal from "sweetalert2";
import axios from "axios";

export function show_alert(mensaje,icono,foco=''){
    if(foco != '') {
        document.getElementById(foco).focus();
    }
    Swal.fire({
        title:mensaje,
        icon:icono,
        customClass: {confirmButton: 'btn btn-primary', popup: 'animated zoomin'},
        buttonsStyling:false
    });
}

export function enviarSolicitud(metodo,parametros,url,mensaje){
    axios({method:metodo,url:url,data:parametros}).then(function(respuesta) {
        var status = respuesta.status;        
        if(status === 200) {
            show_alert(mensaje,status);
            window.setTimeout(function () {
                window.location.href='/';    
            }, 1050);
        }
        else {
            show_alert('No se puede enviar la solicitud.', 'warning');
            window.setTimeout(function () {
                window.location.href='/';    
            }, 1000);            
        }
    }).catch(function(error) {          
        show_alert('Error! servidor no responde.','error');
    });
}

export function validarEmail(email) {
   var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
   return re.test(email);
}
