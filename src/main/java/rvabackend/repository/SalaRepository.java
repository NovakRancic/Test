package rvabackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rvabackend.model.Bioskop;
import rvabackend.model.Sala;


public interface SalaRepository extends JpaRepository<Sala, Integer>{
	
	List<Sala> findByBioskop(Bioskop bioskop);
}