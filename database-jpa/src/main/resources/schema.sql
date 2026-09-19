
DROP TABLE IF EXISTS "books" CASCADE;
DROP TABLE IF EXISTS "authors" CASCADE;

DROP SEQUENCE IF EXISTS "authors_id_seq";

CREATE SEQUENCE "authors_id_seq"
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 1;

CREATE TABLE "authors" (
                           "id" bigint DEFAULT nextval('authors_id_seq') NOT NULL,
                           "name" text,
                           "age" integer,
                           CONSTRAINT "authors_pkey" PRIMARY KEY ("id")
);

CREATE TABLE "books" (
                         "isbn" text NOT NULL,
                         "title" text,
                         "author_id" bigint,
                         CONSTRAINT "books_pkey" PRIMARY KEY ("isbn"),
                         CONSTRAINT "fk_author" FOREIGN KEY ("author_id") REFERENCES authors(id)
);