package com.example.deckstats_clone;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Deck {
    @Id
    private String title;
    private String author;
    private String cards;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }
}
