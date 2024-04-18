package rvabackend.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rvabackend.model.Bioskop;
import rvabackend.model.Sala;
import rvabackend.service.BioskopService;
import rvabackend.service.SalaService;

@RestController
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private BioskopService bioskopService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/sala")
	public List<Sala> getAll() {
		return salaService.getAll();
	}
	
	@GetMapping("/sala/{id}")
	public ResponseEntity<Sala> getOne(@PathVariable("id") Integer id) {
		if(salaService.existById(id)) {
			Optional<Sala> sala = salaService.findById(id);
			return new ResponseEntity<>(sala.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/salaPoBioskopu/{id}")
	public ResponseEntity<List<Sala>> getByBioskop(@PathVariable("id") Integer id) {
		Optional<Bioskop> bioskop = bioskopService.findById(id);
		if(bioskop.isPresent()) {
			List<Sala> salas = salaService.findByBioskop(bioskop.get());
			return new ResponseEntity<>(salas, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/sala")
    public ResponseEntity<Sala> addOne(@RequestBody Sala sala) {
		if (!bioskopService.existsById(sala.getBioskop().getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Sala savedSala = salaService.save(sala);
        URI location = URI.create("/sala/" + savedSala.getId());
        return ResponseEntity.created(location).body(savedSala);
    }
	
	@PutMapping("/sala/{id}")
	public ResponseEntity<Sala> updateSala(@RequestBody Sala sala, @PathVariable("id") Integer id) {
		if(salaService.existById(id)) {
			sala.setId(id);
			Sala savedSala = salaService.save(sala);
			return ResponseEntity.ok().body(savedSala);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/sala/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id){
		if(id == -100 && !salaService.existById(id)) {
			jdbcTemplate.execute("INSERT INTO sala (\"id\", \"broj_redova\", \"kapacitet\", \"bioskop\") " + 
		" VALUES (-100, -100, -100, 1 )");
		}
		
		if(salaService.existById(id)) {
			salaService.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	
}