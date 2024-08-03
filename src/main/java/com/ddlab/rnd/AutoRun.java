package com.ddlab.rnd;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.Author;
import com.ddlab.rnd.entity.Book;
import com.ddlab.rnd.repository.AuthorRepository;

@Component
public class AutoRun {
	
	@Autowired
	private AuthorRepository authorRepo;
	
	//For Bidirectional
	public void saveAllAuthors() {
		Author author1 = new Author("Author-1");
		Author author2 = new Author("Author-2");
		Set<Author> authorSet = new HashSet<>();
		authorSet.add(author1); authorSet.add(author2);
			
		Set<Book> bookSet1 = new HashSet<>();
		Book book1 = new Book("Book-1");
		book1.setAuthors(authorSet);
		author1.setBooks(bookSet1);
		bookSet1.add(book1);
			
		authorRepo.saveAll(authorSet);
	}
	
	public void showAuthorDetail() {
		Author author = authorRepo.findById(2L).get();
		System.out.println("Author: "+author);
		author.getBooks().forEach(book -> System.out.println("Book: "+book));
	}

	
//	public void saveAuthor() {
//		Author author1 = new Author("Author-2");
//		Book book1 = new Book("Book-2");
//		author1.setBook(book1);
//		book1.setAuthor(author1);
//		
//		authorRepo.save(author1);
//		
//	}
	
	
	@EventListener(ApplicationReadyEvent.class)
	public void run() {
		System.out.println("Application running ...");
		saveAllAuthors();
//		showAuthorDetail();
	}

}
