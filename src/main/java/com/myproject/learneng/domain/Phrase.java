package com.myproject.learneng.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class Phrase implements Comparable<Phrase>{
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    @Column(name = "PHRASE_ID")
    private Long id;

    @Column(name = "PHRASE_TYPE")
    @Enumerated(EnumType.STRING)
    private PhraseType type;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TRANSLATION")
    private String translation;

    private String createdDate = LocalDateTime.now((ZoneId.of("Europe/Moscow"))).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    private String lastModified;

    public Phrase() {}

    public Phrase(PhraseType type, String description, String translation) {
        this.type = type;
        this.description = description;
        this.translation = translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phrase phrase = (Phrase) o;
        return type == phrase.type && Objects.equals(description, phrase.description) && Objects.equals(translation, phrase.translation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, translation);
    }

    @Override
    public int compareTo(Phrase phrase) {
        return this.description.compareTo(phrase.description);
    }

    public Long getId() {
        return id;
    }

    public PhraseType getType() {
        return type;
    }

    public void setType(PhraseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
}
