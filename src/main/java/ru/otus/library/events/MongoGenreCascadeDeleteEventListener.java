package ru.otus.library.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.library.models.Genre;
import ru.otus.library.repository.book.BookRepository;

@Component
@RequiredArgsConstructor
public class MongoGenreCascadeDeleteEventListener extends AbstractMongoEventListener<Genre> {
    private final BookRepository bookRepository;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Genre> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        val id = source.get("_id").toString();
        bookRepository.deleteAllByGenre_Id(id).subscribe();
    }
}
