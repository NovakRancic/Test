package rvabackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvabackend.model.Bioskop;


public interface BioskopRepository extends JpaRepository<Bioskop, Integer>{
	
	List<Bioskop> findByNazivContainingIgnoreCase(String naziv);
	
}