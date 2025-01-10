package com.awslambda.crud.springbootcrud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awslambda.crud.springbootcrud.entity.Book;
import com.awslambda.crud.springbootcrud.exception.BookNotFoundException;
import com.awslambda.crud.springbootcrud.repo.BookRepository;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookRepository BookRepository;
	
	
	@GetMapping("/getBooks")
	public List<Book> getAllBooks(){
		return BookRepository.findAll();
	}		
	
	
	@PostMapping("/createBook")
	public Book createBook(@RequestBody Book Book) {
		return BookRepository.save(Book);
	}
	
	
	@GetMapping("/getBook/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Book Book = BookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not exist with id :" + id));
		return ResponseEntity.ok(Book);
	}
	
	
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book BookDetails){
		Book Book = BookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not exist with id :" + id));
		
		Book.setBookname(BookDetails.getBookname());
		Book.setAuthor(BookDetails.getAuthor());
		Book.setPrice(BookDetails.getPrice());
		
		Book updatedBook = BookRepository.save(Book);
		return ResponseEntity.ok(updatedBook);
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long id){
		Book Book = BookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not exist with id :" + id));
		
		BookRepository.delete(Book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
