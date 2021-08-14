package com.myproject.learneng.controllers;

import com.myproject.learneng.dao.VideoRepository;
import com.myproject.learneng.domain.Phrase;
import com.myproject.learneng.domain.PhraseType;
import com.myproject.learneng.domain.User;
import com.myproject.learneng.dto.PhraseDto;
import com.myproject.learneng.dto.UserUpdateRequest;
import com.myproject.learneng.service.AuthService;
import com.myproject.learneng.service.PhraseService;
import com.myproject.learneng.service.UserService;
import com.myproject.learneng.util.VideoLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/phrase")
public class PhraseController {
    private PhraseService phraseService;
    private AuthService authService;
    private UserService userService;
    private VideoRepository videoRepository;

    @Autowired
    public PhraseController(PhraseService phraseService, AuthService authService, UserService userService, VideoRepository videoRepository) {
        this.phraseService = phraseService;
        this.authService = authService;
        this.userService = userService;
        this.videoRepository = videoRepository;
    }

    @GetMapping
    public ResponseEntity<List<PhraseDto>> getAllPhrases(){
        List<PhraseDto> phrases = phraseService.listAllPhrases().stream()
                .map(PhraseDto::getPhraseDto).sorted(Comparator.comparing(PhraseDto::getDescription)).
                        collect(Collectors.toList());
        return new ResponseEntity<>(phrases,OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhraseDto> getPhrase(@PathVariable Long id){
            Phrase phrase = phraseService.getPhraseById(id);
            PhraseDto phraseDto = PhraseDto.getPhraseDto(phrase);
        return new ResponseEntity<>(phraseDto,OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deletePhrase(@PathVariable Long id){
            phraseService.deletePhrase(id);
        return new ResponseEntity<String>("phrase is deleted",OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Set<PhraseDto>> createPhrase(@RequestBody PhraseDto phraseDto) throws IOException {
            Phrase phrase = PhraseDto.getPhraseFromDto(phraseDto);
            phraseService.create(phrase);
            videoRepository.addVideo(phrase.getDescription(), VideoLoader.parseVideo(phrase.getDescription(), 1));
            Set<PhraseDto> phrases = phraseService.listAllPhrases().stream().map(PhraseDto::getPhraseDto).collect(Collectors.toSet());
        return new ResponseEntity<>(phrases,OK);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<PhraseDto> updatePhrase(@PathVariable Long id, @RequestBody PhraseDto phraseDto){
            Phrase updatedPhrase = phraseService.updatePhrase(id, phraseDto);
            PhraseDto resultPhrase = PhraseDto.getPhraseDto(updatedPhrase);
        return new ResponseEntity<>(resultPhrase,OK);
    }

    @GetMapping("/idioms")
    public ResponseEntity<Set<PhraseDto>> getAllIdioms(){
        Set<Phrase> phrases = phraseService.listIdioms();
        Set<PhraseDto> phrasesDto = phrases.stream()
                .map(PhraseDto::getPhraseDto).sorted(Comparator.comparing(PhraseDto::getDescription))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        return new ResponseEntity<>(phrasesDto,OK);
    }
    @GetMapping("/phrasalVerbs")
    public ResponseEntity<Set<PhraseDto>> getAllPhrasalVerbs(){
        Set<Phrase> phrases = phraseService.listPhrasalVerbs();
        Set<PhraseDto> phrasesDto = phrases.stream()
                .map(PhraseDto::getPhraseDto).sorted(Comparator.comparing(PhraseDto::getDescription))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        return new ResponseEntity<>(phrasesDto,OK);
    }

    @GetMapping("/addToUsersList/{id}")
    public ResponseEntity<Set<PhraseDto>> addToUsersList(@PathVariable Long id){
            User user = authService.getCurrentUser();
            Set<Phrase> phrases = user.getSetPhrases();
            Phrase phraseById = phraseService.getPhraseById(id);
            phrases.add(phraseById);
            userService.updateUser(user);
            Set<PhraseDto> set = phraseService.listUserPhrases().stream().map(PhraseDto::getPhraseDto).collect(Collectors.toSet());
        return new ResponseEntity<>(set,OK);
    }

    @GetMapping("/video/{description}")
    public byte[] getVideo(@PathVariable String description) throws IOException {
            byte[] b = videoRepository.getVideo(description);
        return b;
    }

    @GetMapping("/changeVideo/{description}/{index}")
    public ResponseEntity<PhraseDto> changeVideo(@PathVariable String description, @PathVariable Integer index) throws IOException {
            videoRepository.updateVideo(description,VideoLoader.parseVideo(description,index));
            Phrase phrase = phraseService.findPhraseByDescription(description);
            PhraseDto phraseDto = PhraseDto.getPhraseDto(phrase);
        return new ResponseEntity<>(phraseDto,OK);
    }
}
