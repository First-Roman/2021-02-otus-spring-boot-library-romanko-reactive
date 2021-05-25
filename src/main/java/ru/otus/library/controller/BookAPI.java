package ru.otus.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.controller.simple.Response;
import ru.otus.library.convertor.book.ConverterBookToBookDTO;
import ru.otus.library.dto.BookDTO;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;
import ru.otus.library.repository.author.AuthorRepository;
import ru.otus.library.repository.book.BookRepository;
import ru.otus.library.repository.genre.GenreRepository;

@RestController
@RequestMapping(value = "/api/book", produces = "application/json")
@RequiredArgsConstructor
public class BookAPI {

    private final ConverterBookToBookDTO converterBookToBookDTO;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @GetMapping(value = "/all")
    public Flux<BookDTO> getAllBooks() {
        bookRepository.findAll().subscribe(book -> System.out.println(book.getTitle()));
        return bookRepository.findAll().map(converterBookToBookDTO::convert);
    }

    @GetMapping(value = "/{id}")
    public Mono<BookDTO> getBookById(@PathVariable("id") String id) {
        return bookRepository.findById(id).map(converterBookToBookDTO::convert);
    }

    @DeleteMapping(value = "/del/{id}")
    public Mono<String> deleteBook(@PathVariable("id") String id) {
        bookRepository.deleteById(id).subscribe();
        return Mono.just(Response.OK.getName());
    }

    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    public Mono<String> updateBook(BookDTO bookDTO) {
        Mono<Author> authorMono = authorRepository.findById(bookDTO.getAuthorId());
        Author author = authorMono.share().block();
        Mono<Genre> genreMono = genreRepository.findById(bookDTO.getGenreId());
        Genre genre = genreMono.share().block();
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setGenre(genre);
        book.setAuthor(author);
        bookRepository.save(book).subscribe();
        return Mono.just(Response.OK.getName());
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public Mono<String> addBook(BookDTO bookDTO) {
        Mono<Author> authorMono = authorRepository.findById(bookDTO.getAuthorId());
        Author author = authorMono.share().block();
        Mono<Genre> genreMono = genreRepository.findById(bookDTO.getGenreId());
        Genre genre = genreMono.share().block();
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setGenre(genre);
        book.setAuthor(author);
        bookRepository.save(book).subscribe();
        return Mono.just(Response.OK.getName());
    }
}
