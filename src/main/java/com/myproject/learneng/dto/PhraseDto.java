package com.myproject.learneng.dto;

import com.myproject.learneng.domain.Phrase;
import com.myproject.learneng.domain.PhraseType;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PhraseDto {
    private Long id;
    private String type;
    private String description;
    private String translation;

    public PhraseDto() {}

    public PhraseDto(Phrase phrase){
        this.id=phrase.getId();
        if(phrase.getType().name().equals("IDIOM")){
            this.type="idiom";
        }else {
            this.type="phrasal verb";
        }
        this.description=phrase.getDescription();
        this.translation=phrase.getTranslation();
    }

    public static PhraseDto getPhraseDto(Phrase phrase){
        return new PhraseDto(phrase);
    }

    public static Phrase getPhraseFromDto(PhraseDto phraseDto){
            Phrase phrase = new Phrase();
            phrase.setType(PhraseType.valueOf(phraseDto.getType()));
            phrase.setDescription(phraseDto.getDescription());
            phrase.setTranslation(phraseDto.getTranslation());
            phrase.setLastModified(LocalDateTime.now((ZoneId.of("Europe/Moscow"))).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return phrase;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
}
