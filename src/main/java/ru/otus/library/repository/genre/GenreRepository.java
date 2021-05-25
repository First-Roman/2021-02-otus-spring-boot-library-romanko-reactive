package ru.otus.library.repository.genre;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.library.models.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

}
