package ru.otus.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.controller.simple.Response;
import ru.otus.library.convertor.genre.ConverterGenreToGenreDTO;
import ru.otus.library.dto.GenreDTO;
import ru.otus.library.models.Genre;
import ru.otus.library.repository.genre.GenreRepository;

@RestController
@RequestMapping(value = "/api/genre", produces = "application/json")
@RequiredArgsConstructor
public class GenreAPI {
    private final ConverterGenreToGenreDTO converterGenreToGenreDTO;
    private final GenreRepository genreRepository;


    @GetMapping("/all")
    public Flux<GenreDTO> getAllGenre() {
        return genreRepository.findAll().map(converterGenreToGenreDTO::convert);
    }

    @GetMapping("/{id}")
    public Mono<GenreDTO> getGenreById(@PathVariable("id") String id) {
        return genreRepository.findById(id).map(converterGenreToGenreDTO::convert);
    }

    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    public Mono<String> updateGenre(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setGenreName(genreDTO.getGenre());
        genre.setId(genreDTO.getId());
        genreRepository.save(genre).subscribe();
        return Mono.just(Response.OK.getName());
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public Mono<String> addGenre(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setGenreName(genreDTO.getGenre());
        genreRepository.save(genre).subscribe();
        return Mono.just(Response.OK.getName());
    }

    @DeleteMapping(value = "/del/{id}")
    public Mono<String> deleteGenre(@PathVariable("id") String id) {
        genreRepository.deleteById(id).subscribe();
        return Mono.just(Response.OK.getName());
    }
}
