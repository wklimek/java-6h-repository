package com.wklimek.database.dao.impl;

import com.wklimek.database.TestDataUtil;
import com.wklimek.database.domain.Author;
import com.wklimek.database.domain.Book;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGenerateCorrectSql(){
        Book book = Book.builder().isbn("my-isbn").title("my-title").authorId(10L).build();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("my-isbn"),
                eq("my-title"),
                eq(10L)
        );
        verify(jdbcTemplate, times(1)).update(any(), any(), any(), any());
    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql(){
        underTest.findOne("some-isbn");
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("some-isbn")
        );
    }

    @Test
    public void testThatFindBookGeneratesCorrectSql(){
        underTest.find();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBookA();
        underTest.update("other-isbn", book);

        verify(jdbcTemplate).update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                "some-isbn-A", "some-title-A", 1L, "other-isbn"
        );
    }

    @Test
    public void testThatDeleteGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBookA();
        underTest.delete(book.getIsbn());
        verify(jdbcTemplate).update(
                "DELETE books WHERE isbn = ?", book.getIsbn()
        );
    }
}
