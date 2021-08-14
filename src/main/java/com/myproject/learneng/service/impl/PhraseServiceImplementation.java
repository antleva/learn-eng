package com.myproject.learneng.service.impl;

import com.myproject.learneng.dao.PhraseRepository;
import com.myproject.learneng.domain.Phrase;
import com.myproject.learneng.domain.PhraseType;
import com.myproject.learneng.domain.User;
import com.myproject.learneng.dto.PhraseDto;
import com.myproject.learneng.exception.CustomErrorType;
import com.myproject.learneng.service.PhraseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PhraseServiceImplementation implements PhraseService {
    private static final Logger logger = Logger.getLogger(PhraseServiceImplementation.class);
    private PhraseRepository phraseRepository;
    private AuthServiceImplementation authServiceImplementation;

    @Autowired
    public PhraseServiceImplementation(PhraseRepository phraseRepository, AuthServiceImplementation authServiceImplementation) {
        this.phraseRepository = phraseRepository;
        this.authServiceImplementation = authServiceImplementation;
    }

    @Override
    public Phrase create(Phrase phrase) {
        logger.info("create phrase " + phrase.getDescription());
        return phraseRepository.save(phrase);
    }

    @Override
    public Set<Phrase> listAllPhrases() {
        logger.info("get all phrases");
        return new HashSet<>(phraseRepository.findAll());
    }

    @Override
    public Set<Phrase> listUserPhrases() {
        logger.info("get all users phrases");
        User user = authServiceImplementation.getCurrentUser();
        return user.getSetPhrases().stream().sorted(Comparator.comparing(Phrase::getDescription)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<Phrase> listIdioms() {
        logger.info("get all idioms");
        return phraseRepository.listIdioms();
    }

    @Override
    public Set<Phrase> listPhrasalVerbs() {
        logger.info("get all phrasal verbs");
        return phraseRepository.listPhrasalVerbs();
    }

    @Override
    public Set<Phrase> listUsersIdioms() {
        logger.info("get all users idioms");
        User user = authServiceImplementation.getCurrentUser();
        return phraseRepository.listUsersIdioms(user.getId());
    }

    @Override
    public Set<Phrase> listUsersPhrasalVerbs() {
        logger.info("get all users phrasal verbs");
        User user = authServiceImplementation.getCurrentUser();
        return phraseRepository.listUsersPhrasalVerbs(user.getId());
    }

    @Override
    public Phrase getPhraseById(Long id) {
        logger.info("get phrase with id =" + id);
        Phrase phrase = phraseRepository.findById(id).orElseThrow(() -> new CustomErrorType("phrase not found"));
        return phrase;
    }

    @Override
    public Phrase updatePhrase(Long id, PhraseDto phraseDto) {
        logger.info("update phrase " + phraseDto.getDescription());
        Phrase phrase = phraseRepository.findById(id).orElseThrow(() -> new CustomErrorType("phrase not found"));
        String description = phraseDto.getDescription();
        String translation = phraseDto.getTranslation();
        PhraseType phraseType = PhraseType.valueOf(phraseDto.getType());
        phrase.setDescription(description);
        phrase.setTranslation(translation);
        phrase.setType(phraseType);
        phrase.setLastModified(LocalDateTime.now((ZoneId.of("Europe/Moscow"))).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return phraseRepository.save(phrase);
    }

    @Override
    public Phrase findPhraseByDescription(String name) {
        logger.info("find phrase " + name);
        return phraseRepository.findByDescription(name).orElseThrow(() -> new CustomErrorType("not found"));
    }

    @Override
    public void deletePhrase(Long id) {
        logger.info("delete phrase with id = " + id);
        phraseRepository.deleteById(id);
    }
}
