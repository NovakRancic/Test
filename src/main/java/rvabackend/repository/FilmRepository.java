package rvabackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvabackend.model.Film;


public interface FilmRepository extends JpaRepository<Film, Integer>{
	
	List<Film> findByNazivContainingIgnoreCase(String naziv);
	List<Film> findByZanrContainingIgnoreCase(String zanr);
}