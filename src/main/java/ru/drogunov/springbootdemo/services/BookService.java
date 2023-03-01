package ru.drogunov.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.drogunov.springbootdemo.model.Book;
import ru.drogunov.springbootdemo.model.Person;
import ru.drogunov.springbootdemo.repositories.BookRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public Page<Book> findAll(PageRequest pageRequest, Boolean sortByYear) {
        Page<Book> all;
        if (sortByYear) {
            all = bookRepository.findAll(pageRequest.withSort(Sort.by("year")));
        } else {
            all = bookRepository.findAll(pageRequest);
        }
        return all;
    }
    
    public Book findOne(Integer id) {
        return bookRepository.findById(id)
                .orElse(null);
    }
    
    public List<Book> searchByTitle(String substringTitle) {
        return bookRepository.findByTitleStartingWithIgnoreCase(substringTitle);
    }
    
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }
    
    @Transactional
    public void update(Book book) {
        bookRepository.save(book);
    }
    
    @Transactional
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }
    
    @Transactional
    public void reserved(Integer bookId, Person person) {
        /*TODO переделать на объекты*/
        bookRepository.findById(bookId)
                .ifPresent(book -> {
                    book.setPerson(person);
                    book.setTakenAt(new Date());
                });
    }
    
    @Transactional
    public void freedom(Integer bookId) {
        bookRepository.findById(bookId)
                .ifPresent(book -> {
                    book.setPerson(null);
                    book.setTakenAt(null);
                });
    }
}
