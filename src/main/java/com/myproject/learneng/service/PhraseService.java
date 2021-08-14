package com.myproject.learneng.service;

import com.myproject.learneng.domain.Phrase;
import com.myproject.learneng.dto.PhraseDto;

import java.util.Set;

public interface PhraseService {
    Set<Phrase> listAllPhrases();

    Set<Phrase> listUserPhrases();

    Set<Phrase> listIdioms();

    Set<Phrase> listPhrasalVerbs();

    Set<Phrase> listUsersPhrasalVerbs();

    Set<Phrase> listUsersIdioms();

    Phrase getPhraseById(Long id);

    Phrase updatePhrase(Long id, PhraseDto phrase);

    Phrase findPhraseByDescription(String description);

    void deletePhrase(Long id);

    Phrase create(Phrase phrase);

}
