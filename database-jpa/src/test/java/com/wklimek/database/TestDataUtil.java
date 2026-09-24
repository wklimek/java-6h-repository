package com.wklimek.database;

import com.wklimek.database.domain.Author;
import com.wklimek.database.domain.Book;

public class TestDataUtil {
    public static Author createTestAuthorA() {
        return Author.builder()
                .name("John")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .name("Tom")
                .age(44)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .name("Ralph")
                .age(24)
                .build();
    }

    public static Book createTestBookA(final Author author) {

        return Book.builder()
                .isbn("some-isbn-A")
                .title("some-title-A")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {

        return Book.builder()
                .isbn("some-isbn-B")
                .title("some-title-B")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {

        return Book.builder()
                .isbn("some-isbn-C")
                .title("some-title-C")
                .author(author)
                .build();
    }
}
