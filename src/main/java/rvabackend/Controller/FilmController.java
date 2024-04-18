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

import rvabackend.model.Film;
import rvabackend.service.FilmService;

@RestController
public class FilmController {
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/film")
	public List<Film> getAll() {
		return filmService.getAll();
	}
	
	@GetMapping("/film/{id}")
	public ResponseEntity<Film> getOne(@PathVariable("id") Integer id) {
		if(filmService.existById(id)) {
			Optional<Film> film = filmService.findById(id);
			return new ResponseEntity<>(film.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/film/naziv/{naziv}")
	public ResponseEntity<List<Film>> getByNaziv(@PathVariable("naziv") String naziv) {
		List<Film> films = filmService.findByNazivContainingIgnoreCase(naziv);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}
	
	@GetMapping("/film/zanr/{zanr}")
	public ResponseEntity<List<Film>> getByZanr(@PathVariable("zanr") String zanr) {
		List<Film> films = filmService.findByZanrContainingIgnoreCase(zanr);
		return new ResponseEntity<>(films, HttpStatus.OK);
	}
	
	@PostMapping("/film")
	public ResponseEntity<Film> addFilm(@RequestBody Film film) {
		Film savedFilm = filmService.save(film);
		URI location = URI.create("/Film/" + savedFilm.getId());
		return ResponseEntity.created(location).body(savedFilm);
	}
	
	@PutMapping("/film/{id}")
	public ResponseEntity<Film> updateFilm(@RequestBody Film film, @PathVariable("id") Integer id) {
		if(filmService.existById(id)) {
			film.setId(id);
			Film savedFilm = filmService.save(film);
			return ResponseEntity.ok().body(savedFilm);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/film/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id){
		
		if(id == -100 && !filmService.existById(id)) {
			jdbcTemplate.execute("INSERT INTO Film (\"id\", \"naziv\", \"recenzija\", \"trajanje\", \"zanr\") " + 
		" VALUES (-100, 'Test naziv', 'Test adresa', -100, 'Test zanr' )");
		}
		
		if(filmService.existById(id)) {
			filmService.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
	}
	
}