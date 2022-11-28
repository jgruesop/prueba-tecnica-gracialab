package com.restaurant.reservaservice.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reserva")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Reserva implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombres;
    private String apellidos;
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    private Integer identificacion;
    private String email;
    @Column(name = "fecha_reserva")
    //@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate fechaReserva;
    @Column(name = "tipo_reserva")
    private String tipoReserva;
    @Column(name = "cantidad_personas")
    private int cantidadPersonas;
    private String descripcion;
    private String estado;
}
