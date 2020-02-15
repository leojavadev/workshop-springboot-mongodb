package br.com.fling.workshopmongo.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fling.workshopmongo.domain.User;
import br.com.fling.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;	

	@Override
	public void run(String... args) throws Exception {
		//Limpar os dados existentes no banco
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex, bob));
		userRepository.saveAll(list);
	} 
}
