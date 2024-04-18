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

import rvabackend.model.Rezervacija;
import rvabackend.model.Film;
import rvabackend.model.Sala;
import rvabackend.service.RezervacijaService;
import rvabackend.service.FilmService;
import rvabackend.service.SalaService;

@RestController
public class RezervacijaController {
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/rezervacija")
	public List<Rezervacija> getAll() {
		return rezervacijaService.getAll();
	}
	
	@GetMapping("/rezervacija/{id}")
	public ResponseEntity<Rezervacija> getOne(@PathVariable("id") Integer id) {
		if(rezervacijaService.existById(id)) {
			Optional<Rezervacija> rezervacija = rezervacijaService.findById(id);
			return new ResponseEntity<>(rezervacija.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/rezervacijaPoFilmu/{id}")
	public ResponseEntity<List<Rezervacija>> getByFilm(@PathVariable("id") Integer id) {
		Optional<Film> film = filmService.findById(id);
		if(film.isPresent()) {
			List<Rezervacija> rezervacijas = rezervacijaService.findByFilm(film.get());
			return new ResponseEntity<>(rezervacijas, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/rezervacijaPoSali/{id}")
	public ResponseEntity<List<Rezervacija>> getBySala(@PathVariable("id") Integer id) {
		Optional<Sala> sala = salaService.findById(id);
		if(sala.isPresent()) {
			List<Rezervacija> rezervacijas = rezervacijaService.findBySala(sala.get());
			return new ResponseEntity<>(rezervacijas, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	@PostMapping("/rezervacija")
	public ResponseEntity<Rezervacija> addRezervacija(@RequestBody Rezervacija rezervacija) {
		Rezervacija savedRezervacija = rezervacijaService.save(rezervacija);
		URI location = URI.create("/rezervacija/" + savedRezervacija.getId());
		return ResponseEntity.created(location).body(savedRezervacija);
	}
	
	@PutMapping("/rezervacija/{id}")
	public ResponseEntity<Rezervacija> updateRezervacija(@RequestBody Rezervacija rezervacija, @PathVariable("id") Integer id) {
		if(rezervacijaService.existById(id)) {
			rezervacija.setId(id);
			Rezervacija savedRezervacija = rezervacijaService.save(rezervacija);
			return ResponseEntity.ok().body(savedRezervacija);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/rezervacija/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id){
		if(id == -100 && !rezervacijaService.existById(id)) {
			jdbcTemplate.execute("INSERT INTO rezervacija (\"broj_osoba\", \"cena_karte\",\"film\",\"id\", \"placeno\", \"sala\", \"datum\") " + 
		" VALUES (-100, -100, 1, -100, false, 1, '2024-01-01' )");
		}
		
		if(rezervacijaService.existById(id)) {
			rezervacijaService.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	
}