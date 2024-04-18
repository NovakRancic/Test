package rvabackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rvabackend.model.Bioskop;
import rvabackend.model.Sala;
import rvabackend.repository.SalaRepository;

@Service
public class SalaService {
	
	@Autowired
	private SalaRepository salaRepository;
	
	public List<Sala> getAll() {
		return salaRepository.findAll();
	}
	
	public Optional<Sala> findById(Integer id){
		return salaRepository.findById(id);
	}
	
	public List<Sala> findByBioskop(Bioskop bioskop){
		return salaRepository.findByBioskop(bioskop);
	}
	
	public Sala save (Sala sala) {
		return salaRepository.save(sala);
	}
	
	public boolean existById(Integer id) {
		return salaRepository.existsById(id);
	}
	
	public void deleteById(Integer id) {
		salaRepository.deleteById(id);
	}

}