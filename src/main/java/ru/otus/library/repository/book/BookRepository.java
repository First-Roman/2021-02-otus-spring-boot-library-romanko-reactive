package ru.otus.library.repository.book;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import ru.otus.library.models.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findByTitle(String title);

    Flux<Book> findAllBookByAuthor(@Param("authorId") String authorId);

    Flux<Book> findAllBooksByGenre(@Param("genreId") String genreId);

    Flux<Book> deleteAllByAuthor_Id(@Param("authorId") String authorId);

    Flux<Book> deleteAllByGenre_Id(@Param("genreId") String genreId);
}
