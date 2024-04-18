package rvabackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rvabackend.model.Film;
import rvabackend.repository.FilmRepository;

@Service
public class FilmService {
	
	@Autowired
	private FilmRepository filmRepository;
	
	public List<Film> getAll() {
		return filmRepository.findAll();
	}
	
	public Optional<Film> findById(Integer id){
		return filmRepository.findById(id);
	}
	
	public List<Film> findByNazivContainingIgnoreCase(String naziv){
		return filmRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	public List<Film> findByZanrContainingIgnoreCase(String zanr){
		return filmRepository.findByZanrContainingIgnoreCase(zanr);
	}
	
	public Film save (Film Film) {
		return filmRepository.save(Film);
	}
	
	public boolean existById(Integer id) {
		return filmRepository.existsById(id);
	}
	
	public void deleteById(Integer id) {
		filmRepository.deleteById(id);
	}

}