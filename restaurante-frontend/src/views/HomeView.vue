<template>
  <div class="row m-3 mx-50 d-block justify-content-center">
    <div class="col-md-6 offset-md-3">
      <div class="card">
        <div class="card-header bg-dark text-white text-center">
          <h1>SOLICITAR RESERVACIÓN</h1>
        </div>
        <div class="card body">
          <form v-on:submit="guardar">
            <div class="form-group m-3">
              <label for="nombres" class="control-label">Nombres :</label>
              <input type="text" id="nombres" v-model="nombres" class="form-control" required>
            </div>

            <div class="form-group m-3">
              <label for="apellidos" class="control-label">Apellidos :</label>
              <input type="text" id="apellidos" v-model="apellidos" class="form-control" required>
            </div>

            <div class="form-group col-md-6 m-3">
              <label for="tipoDocumento" class="form-label">Tipo de Documento :</label>
              <select class="form-select" v-model="tipoDocumento" id="tipoDocumento" required>
                <option selected disabled value="">Elija...</option>
                <option>C.C</option>
                <option>T.I</option>
                <option>C.E</option>
              </select>
              <div class="invalid-feedback">
                Please select a valid state.
              </div>
            </div>

            <div class="form-group m-3">
              <label for="identificacion" class="control-label">Identificaci&oacute;n :</label>
              <input type="number" id="identificacion" v-model="identificacion" class="form-control">
            </div>

            <div class="form-group m-3">
              <label for="email" class="control-label">Email :</label>
              <input type="email" id="email" v-model="email" class="form-control" required>
            </div>

            <div class="form-group col-md-6 m-3">
              <label for="fechaReserva" class="control-label">Fecha reservaci&oacute;n :</label>
              <input type="date" id="fechaReserva" v-model="fechaReserva" class="form-control" required>
            </div>

            <div class="form-group col-md-6 m-3">
              <label for="tipoReserva" class="form-label">Tipo de reserva :</label>
              <select class="form-select" v-model="tipoReserva" id="tipoReserva" required>
                <option selected disabled value="">Elija...</option>
                <option>Cena</option>
                <option>Almuerzo</option>
                <option>Onces</option>
                <option>Cumpleaños</option>
                <option>Ocasiones especiales</option>
              </select>
              <div class="invalid-feedback">
                Please select a valid state.
              </div>
            </div>

            <div class="form-group m-3">
              <label for="cantidadPersonas" class="control-label">Cantidad de personas :</label>
              <input type="number" id="cantidadPersonas" v-model="cantidadPersonas" class="form-control">
            </div>

            <div class="form-group m-3">
              <label for="descripcion" class="control-label">Descripci&oacute;n :</label>
              <input type="text" id="descripcion" v-model="descripcion" class="form-control">
            </div>
            <div class="d-inline mx-auto">
              <button type="submit" class="btn w-50 btn-primary m-3 r btn-lg">Reservar</button>
              <button type="reset" class="btn btn-danger m-3 btn-lg">Cancelar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <br>
</template>
<script>
  import { show_alert, enviarSolicitud, validarEmail } from "../funciones";
  export default {
    data() {
      return {
        nombres: '', apellidos: '', tipoDocumento: '', identificacion: '', email: '', fechaReserva: '', tipoReserva: '', cantidadPersonas: '', descripcion: '', estado: 'pendiente', url: 'http://localhost:8000/api/reserva'
      }
    },
    methods: {
      guardar() {
        event.preventDefault();
        if (this.nombres.trim() === '') {
          show_alert('Ingrese sus nombres', 'warning', 'nombres');
        }
        else if (this.apellidos.trim() === '') {
          show_alert('Ingrese sus apellidos', 'warning', 'apellidos');
        }
        else if (this.identificacion <= 0 || this.identificacion === '') {
          show_alert('Ingrese una identificacion valida', 'warning', 'identificacion');
        }
        else if (this.email.trim() === '' || !validarEmail(this.email.trim())) {
          show_alert('Valide su email', 'warning', 'email');
        }
        else if (this.fechaReserva.trim() === '') {
          show_alert('Ingrese la fecha Reserva', 'warning', 'fechaReserva');
        }
        else if (this.cantidadPersonas <= 0 || this.cantidadPersonas === '') {
          show_alert('La cantidad de Personas debe ser mayor a cero(0)', 'warning', 'cantidadPersonas');
        }        
        else {
          var parametros = { nombres: this.nombres.trim().toUpperCase(), apellidos: this.apellidos.trim().toUpperCase(), tipoDocumento: this.tipoDocumento.trim().toUpperCase(), identificacion: this.identificacion, email: this.email.trim(), fechaReserva: this.fechaReserva.trim(), tipoReserva: this.tipoReserva.trim().toUpperCase(), cantidadPersonas: this.cantidadPersonas, descripcion: this.descripcion.trim().toUpperCase(), estado: this.estado.trim().toUpperCase() };

          enviarSolicitud('POST', parametros, this.url, 'Reservación enviada');
        }
      }
    }
  }
</script>
