package com.restaurant.reservaservice.service;

import com.restaurant.reservaservice.model.entity.Reserva;
import com.restaurant.reservaservice.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaServiceImplement implements IReservaService{

    @Autowired
    ReservaRepository reservaRepository;
    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }
    @Override
    @Transactional
    public Reserva guardar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva findById(Integer id) {
        return reservaRepository.findById(id).orElse(null);
    }

}
