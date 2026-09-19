package com.wklimek.database;

import com.wklimek.database.domain.Author;
import com.wklimek.database.domain.Book;

public class TestDataUtil {
    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("John")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Tom")
                .age(60)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Ralph")
                .age(31)
                .build();
    }

    public static Book createTestBookA(){

        return Book.builder()
                .isbn("some-isbn-A")
                .title("some-title-A")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB(){

        return Book.builder()
                .isbn("some-isbn-B")
                .title("some-title-B")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookC(){

        return Book.builder()
                .isbn("some-isbn-C")
                .title("some-title-C")
                .authorId(1L)
                .build();
    }
}
