package ru.otus.library.repository.author;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.library.models.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

}
