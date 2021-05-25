package ru.otus.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BookDTO {

    private String id;

    private String authorId;

    private String genreId;

    private String title;

    private String author;

    private String genre;

}
