package BookingSystem.Services.Impl;

import BookingSystem.Reporitories.AuthorRepository;
import BookingSystem.Services.AuthorService;
import BookingSystem.data.Author;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String FILE_PATH = "src/main/resources/files/authors.txt";

    private final AuthorRepository  authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(FILE_PATH))
                .stream()
                .filter(row -> !row.isEmpty())
                .forEach(row -> {
                    String[] token = row.split("\\s+");
                    Author author = new Author(token[0], token[1]);
                    this.authorRepository.saveAndFlush(author);
                });
    }

    @Override
    public Author getRandomAuthor() {
    int randomId= ThreadLocalRandom.current().nextInt(1,(int) authorRepository.count()+1);
        return this.authorRepository.findById(randomId).get();
    }
}