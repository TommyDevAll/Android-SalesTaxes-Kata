package it.tommasoresti.salestaxes.domain.article;

public class Book extends Item {
    public Book(String description, float price) {
        super("book", description, price);
    }
}
