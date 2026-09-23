package com.wklimek.database.repositories;

import com.wklimek.database.TestDataUtil;
import com.wklimek.database.domain.Author;
import com.wklimek.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {

    private final BookRepository underTest;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest, AuthorRepository authorRepository) {
        this.underTest = underTest;
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthorA();

        Book book = TestDataUtil.createTestBookA(author);
        Book savedBook = underTest.save(book);
        Optional<Book> result = underTest.findById(savedBook.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedBook);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = authorRepository.save(TestDataUtil.createTestAuthorA());

        Book bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);
        Book bookB = TestDataUtil.createTestBookB(author);
        underTest.save(bookB);
        Book bookC = TestDataUtil.createTestBookC(author);
        underTest.save(bookC);

        Iterable<Book> result = underTest.findAll();

        assertThat(result)
                .hasSize(3)
                .containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeUpdated(){
        Author author = authorRepository.save(TestDataUtil.createTestAuthorA());

        Book bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);
        bookA.setTitle("Changed title");

        underTest.save(bookA);
        Optional<Book> result = underTest.findById(bookA.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = authorRepository.save(TestDataUtil.createTestAuthorA());

        Book bookA = TestDataUtil.createTestBookA(author);
        underTest.save(bookA);

        underTest.deleteById(bookA.getIsbn());
        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isEmpty();
    }
}
