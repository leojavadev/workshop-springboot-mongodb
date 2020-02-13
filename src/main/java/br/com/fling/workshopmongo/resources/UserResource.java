package br.com.fling.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fling.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@RequestMapping(method = RequestMethod.GET)
	//ou
	//@GetMapping
	public ResponseEntity<List<User>> findAll(){
		User maria = new User("1", "Maria", "maria@fling.com");
		User joao = new User("2", "João", "joao@fling.com");
		User erica = new User("3", "Érica", "erica@fling.com.br");
		List<User> users = new ArrayList<>();
		users.addAll(Arrays.asList(maria, joao, erica));
		return ResponseEntity.ok().body(users);
	}

}