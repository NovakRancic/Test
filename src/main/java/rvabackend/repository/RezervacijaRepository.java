package rvabackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvabackend.model.Film;
import rvabackend.model.Rezervacija;
import rvabackend.model.Sala;



public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer>{
	
	List<Rezervacija> findByFilm(Film film);
	List<Rezervacija> findBySala(Sala sala);
	List<Rezervacija> findByPlacenoTrue();
}