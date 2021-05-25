package ru.otus.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.library.controller.simple.Response;
import ru.otus.library.convertor.author.ConverterAuthorToAuthorDTO;
import ru.otus.library.dto.AuthorDTO;
import ru.otus.library.models.Author;
import ru.otus.library.repository.author.AuthorRepository;

@RestController
@RequestMapping(value = "/api/author", produces = "application/json")
@RequiredArgsConstructor
public class AuthorAPI {

    private final ConverterAuthorToAuthorDTO converterAuthorToAuthorDTO;
    private final AuthorRepository authorRepository;

    @GetMapping("/all")
    public Flux<AuthorDTO> getAllAuthor() {

        return authorRepository.findAll().map(converterAuthorToAuthorDTO::convert);
    }

    @GetMapping("/{id}")
    public Mono<AuthorDTO> getAuthorById(@PathVariable("id") String id) {
        return authorRepository.findById(id).map(converterAuthorToAuthorDTO::convert);
    }

    @PutMapping(value = "/edit", consumes = {"multipart/form-data"})
    public Mono<String> updateAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setMiddleName(authorDTO.getMiddleName());
        author.setLastName(authorDTO.getMiddleName());
        author.setId(authorDTO.getId());
        authorRepository.save(author).subscribe();
        return Mono.just(Response.OK.getName());
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public Mono<String> addAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setMiddleName(authorDTO.getMiddleName());
        author.setLastName(authorDTO.getMiddleName());
        authorRepository.save(author).subscribe();
        return Mono.just(Response.OK.getName());
    }

    @DeleteMapping(value = "/del/{id}")
    public Mono<String> deleteAuthor(@PathVariable("id") String id) {
        authorRepository.deleteById(id).subscribe();
        return Mono.just(Response.OK.getName());
    }
}
