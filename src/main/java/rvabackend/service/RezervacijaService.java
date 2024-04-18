package rvabackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rvabackend.model.Film;
import rvabackend.model.Rezervacija;
import rvabackend.model.Sala;
import rvabackend.repository.RezervacijaRepository;

@Service
public class RezervacijaService {
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	public List<Rezervacija> getAll() {
		return rezervacijaRepository.findAll();
	}
	
	public Optional<Rezervacija> findById(Integer id){
		return rezervacijaRepository.findById(id);
	}
	
	public List<Rezervacija> findByFilm(Film film){
		return rezervacijaRepository.findByFilm(film);
	}
	
	public List<Rezervacija> findBySala(Sala sala){
		return rezervacijaRepository.findBySala(sala);
	}
	
	public List<Rezervacija> findByPlacenoTrue(){
		return rezervacijaRepository.findByPlacenoTrue();
	}
	
	public Rezervacija save (Rezervacija Rezervacija) {
		return rezervacijaRepository.save(Rezervacija);
	}
	
	public boolean existById(Integer id) {
		return rezervacijaRepository.existsById(id);
	}
	
	public void deleteById(Integer id) {
		rezervacijaRepository.deleteById(id);
	}

}