package com.restaurant.reservaservice.service;

import com.restaurant.reservaservice.model.entity.Reserva;

import java.util.List;
public interface IReservaService {

    public List<Reserva> listarReservas();
    Reserva guardar(Reserva reserva);
    Reserva findById(Integer id);
}
