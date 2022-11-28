package com.restaurant.reservaservice.controller;

import com.restaurant.reservaservice.model.entity.Reserva;
import com.restaurant.reservaservice.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reserva")
public class ReservaController {

    @Autowired
    private IReservaService iReservaService;

    @GetMapping
    public ResponseEntity<List<Reserva>> listarReservas(@RequestParam(name = "id", required = false) Integer id) {
        List<Reserva> reservas = new ArrayList<>();
        if(id == null) {
            reservas = iReservaService.listarReservas();
        } else {
            if(reservas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable("id") int id){
        Reserva reserva = iReservaService.findById(id);
        if(reserva == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reserva);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void crearReserva(@RequestBody Reserva reserva) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        reserva.setFechaReserva(LocalDate.parse(reserva.getFechaReserva().format(formato)));
        System.out.println(reserva.getTipoReserva());
        iReservaService.guardar(reserva);
    }
}
