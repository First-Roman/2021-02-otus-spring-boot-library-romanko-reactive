package ru.otus.library.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Comment;
import ru.otus.library.models.Genre;
import ru.otus.library.repository.author.AuthorRepository;
import ru.otus.library.repository.book.BookRepository;
import ru.otus.library.repository.comment.CommentRepository;
import ru.otus.library.repository.genre.GenreRepository;

import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "FirstRoman", runAlways = true)
    public void dropDb(MongoDatabase mongoDatabase) {
        mongoDatabase.drop();
    }

    @ChangeSet(order = "002", id = "insertData", author = "FirstRoman", runAlways = true)
    public void insertData(AuthorRepository authorRepository, GenreRepository genreRepository, BookRepository bookRepository, CommentRepository commentRepository) {
        Author author = new Author();
        author.setId("1");
        author.setLastName("Толстой");
        author.setMiddleName("Николаевич");
        author.setFirstName("Лев");
        Genre genre = new Genre();
        genre.setId("1");
        genre.setGenreName("Классика");
        Book book = new Book();
        book.setId("1");
        book.setGenre(genre);
        book.setAuthor(author);
        book.setTitle("Война и мир");
        Book bookTolstoy = new Book();
        bookTolstoy.setId("3");
        bookTolstoy.setTitle("Анна Каренина");
        bookTolstoy.setGenre(genre);
        bookTolstoy.setAuthor(author);
        Comment comment = new Comment();
        comment.setNikName("FirstRoman");
        comment.setComment("Nice!");
        comment.setBookId(book.getId());
        Author author1 = new Author();
        author1.setId("2");
        author1.setFirstName("Александр");
        author1.setLastName("Пушкин");
        author1.setMiddleName("Сергеевич");
        Book bookPushkin = new Book();
        bookPushkin.setId("2");
        bookPushkin.setTitle("Евгений Онегин");
        bookPushkin.setAuthor(author1);
        bookPushkin.setGenre(genre);
        genreRepository.saveAll(List.of(genre)).subscribe();
        authorRepository.saveAll(List.of(author, author1)).subscribe();
        bookRepository.saveAll(List.of(book, bookTolstoy, bookPushkin)).subscribe(book1 -> System.out.println(book1.getTitle()));
        System.out.println("По идеии все должно сработать");
    }

}
