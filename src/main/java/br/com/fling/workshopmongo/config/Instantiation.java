package br.com.fling.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fling.workshopmongo.domain.Post;
import br.com.fling.workshopmongo.domain.User;
import br.com.fling.workshopmongo.dto.AuthorDTO;
import br.com.fling.workshopmongo.dto.CommentDTO;
import br.com.fling.workshopmongo.repository.PostRepository;
import br.com.fling.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		//Limpar os dados existentes no banco
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
					
		Post post1 = new Post(null, sdf.parse("20-02-2020"), "Post1", "Hello world", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("21-02-2020"), "Post2", "Tenham uma boa semana!", new AuthorDTO(maria));
		Post post3 = new Post(null, sdf.parse("21-02-2020"), "Post3", "Bora codar!", new AuthorDTO(alex));
		Post post4 = new Post(null, sdf.parse("21-02-2010"), "Post4", "Bonjour", new AuthorDTO(bob));
		
		CommentDTO c1 = new CommentDTO("Boa!", sdf.parse("23-02-2020"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Massa!", sdf.parse("23-02-2020"), new AuthorDTO(alex));
		CommentDTO c3 = new CommentDTO("Vai ser show!", sdf.parse("23-02-2020"), new AuthorDTO(alex));
		CommentDTO c4 = new CommentDTO("Só vai dar nóis", sdf.parse("23-02-2020"), new AuthorDTO(bob));
		CommentDTO c5 = new CommentDTO("Tamos junto", sdf.parse("23-02-2020"), new AuthorDTO(bob));
		CommentDTO c6 = new CommentDTO("A galera vai curtir", sdf.parse("23-02-2020"), new AuthorDTO(bob));
		CommentDTO c7 = new CommentDTO("Tudo de bom pra vocês!!!", sdf.parse("23-02-2020"), new AuthorDTO(maria));
		CommentDTO c8 = new CommentDTO("Beijos!!!", sdf.parse("23-02-2020"), new AuthorDTO(maria));
		CommentDTO c9 = new CommentDTO("Vai ser bom demais!!!", sdf.parse("23-02-2020"), new AuthorDTO(maria));
		
		post1.getComments().addAll(Arrays.asList(c1, c4));
		post2.getComments().addAll(Arrays.asList(c2, c5));
		post3.getComments().addAll(Arrays.asList(c6, c7));
		post4.getComments().addAll(Arrays.asList(c3, c9));
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));		
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		alex.getPosts().add(post3);
		userRepository.save(alex);
		bob.getPosts().add(post4);
		userRepository.save(bob);
	} 
}
