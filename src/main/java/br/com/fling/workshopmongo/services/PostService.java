package br.com.fling.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fling.workshopmongo.domain.Post;
import br.com.fling.workshopmongo.repository.PostRepository;
import br.com.fling.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado!"));
	}
	
	//consulta simples com query methods
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
	}
	
	//consulta simples com @Query
	public List<Post> findPostByTitle(String text){
		return repo.searchTitle(text);
	}
}
