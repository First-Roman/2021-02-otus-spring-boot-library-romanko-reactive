package ru.otus.library.repository.comment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.library.models.Comment;

import java.util.List;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {

    List<Comment> findAllByBookId(String bookId);

    void deleteByBookId(String bookId);

}
