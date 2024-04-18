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
import rvabackend.service.BioskopService;

@RestController
public class BioskopController {
	
	@Autowired
	private BioskopService bioskopService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/bioskop")
	public List<Bioskop> getAll() {
		return bioskopService.getAll();
	}
	
	@GetMapping("/bioskop/{id}")
	public ResponseEntity<Bioskop> getOne(@PathVariable("id") Integer id) {
		if(bioskopService.existsById(id)) {
			Optional<Bioskop> bioskop = bioskopService.findById(id);
			return new ResponseEntity<>(bioskop.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/bioskop/naziv/{naziv}")
	public ResponseEntity<List<Bioskop>> getByNaziv(@PathVariable("naziv") String naziv) {
		List<Bioskop> bioskops = bioskopService.findByNazivContainingIgnoreCase(naziv);
		return new ResponseEntity<>(bioskops, HttpStatus.OK);
	}
	
	@PostMapping("/bioskop")
	public ResponseEntity<Bioskop> addBioskop(@RequestBody Bioskop bioskop) {
		Bioskop savedBioskop = bioskopService.save(bioskop);
		URI location = URI.create("/bioskop/" + savedBioskop.getId());
		return ResponseEntity.created(location).body(savedBioskop);
	}
	
	@PutMapping("/bioskop/{id}")
	public ResponseEntity<Bioskop> updateBioskop(@RequestBody Bioskop bioskop, @PathVariable("id") Integer id) {
		if(bioskopService.existsById(id)) {
			bioskop.setId(id);
			Bioskop savedBioskop = bioskopService.save(bioskop);
			return ResponseEntity.ok().body(savedBioskop);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/bioskop/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id){
		
		if(id == -100 && !bioskopService.existsById(id)) {
			jdbcTemplate.execute("INSERT INTO bioskop (\"id\", \"naziv\", \"adresa\") " + 
		" VALUES (-100, 'Test naziv', 'Test adresa' )");
		}
		
		if(bioskopService.existsById(id)) {
			bioskopService.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	
}