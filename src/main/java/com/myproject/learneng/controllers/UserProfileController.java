package com.myproject.learneng.controllers;


import com.myproject.learneng.domain.Phrase;
import com.myproject.learneng.domain.Result;
import com.myproject.learneng.domain.User;
import com.myproject.learneng.dto.PhraseDto;
import com.myproject.learneng.dto.ResultDto;
import com.myproject.learneng.dto.UserDto;
import com.myproject.learneng.service.AuthService;
import com.myproject.learneng.service.PhraseService;
import com.myproject.learneng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("profile")
public class UserProfileController {
    private AuthService authService;
    private PhraseService phraseService;
    private UserService userService;
    @Autowired
    public UserProfileController(AuthService authService, PhraseService phraseService, UserService userService) {
        this.authService = authService;
        this.phraseService = phraseService;
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<UserDto> showMyProfile(){
        User user = authService.getCurrentUser();
        UserDto userDto = UserDto.getUserDto(user);
        return new ResponseEntity<>(userDto,OK);
    }

    @GetMapping("/results")
    ResponseEntity<List<String>> showMyResults(){
        User user = authService.getCurrentUser();
        List<String> set = user.getSetResults().stream().map(ResultDto::getResultDto).collect(Collectors.toList());
        return new ResponseEntity<>(set,OK);
    }
    @GetMapping("/allUsersPhrases")
    ResponseEntity<Set<PhraseDto>> showUsersPhrases(){
        Set<PhraseDto> set = phraseService.listUserPhrases().stream()
                .map(PhraseDto::getPhraseDto).sorted(Comparator.comparing(PhraseDto::getDescription))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        return new ResponseEntity<>(set,OK);
    }

    @GetMapping("/allUsersIdioms")
    ResponseEntity<Set<PhraseDto>> showUsersIdioms(){
        Set<PhraseDto> set = phraseService.listUsersIdioms().stream()
                .map(PhraseDto::getPhraseDto).sorted(Comparator.comparing(PhraseDto::getDescription))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        return new ResponseEntity<>(set,OK);
    }
    @GetMapping("/allUsersPhrasalVerbs")
    ResponseEntity<Set<PhraseDto>> showUsersPhrasalVerbs(){
        Set<PhraseDto> set = phraseService.listUsersPhrasalVerbs().stream()
                .map(PhraseDto::getPhraseDto).sorted(Comparator.comparing(PhraseDto::getDescription))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        return new ResponseEntity<>(set,OK);
    }

    @GetMapping("/learnRandomPhrases")
    ResponseEntity<Set<PhraseDto>> learnPhrases(){
        List<Phrase> list = new ArrayList<>(phraseService.listUserPhrases());
        Collections.shuffle(list);
        Set<PhraseDto> set = list.stream().limit(20).map(PhraseDto::getPhraseDto).collect(Collectors.toSet());
        return new ResponseEntity<>(set,OK);
    }

    @PostMapping("/addResult")
    public ResponseEntity<Set<PhraseDto>> addResult(@RequestBody ResultDto resultDto){
        User user = authService.getCurrentUser();
        Result result = ResultDto.getResultFromDto(resultDto);
        user.getSetResults().add(result);
        userService.updateUser(user);
        Set<PhraseDto> set = phraseService.listUserPhrases().stream().map(PhraseDto::getPhraseDto).collect(Collectors.toSet());
        return new ResponseEntity<>(set,OK);
    }
    @DeleteMapping("/{id}/deletePhrase")
    public ResponseEntity<Set<PhraseDto>> deletePhrase(@PathVariable Long id){
        User user = authService.getCurrentUser();
        Phrase phraseById = phraseService.getPhraseById(id);
        user.getSetPhrases().remove(phraseById);
        userService.updateUser(user);
        Set<PhraseDto> set = phraseService.listUserPhrases().stream().map(PhraseDto::getPhraseDto).collect(Collectors.toSet());
        return new ResponseEntity<>(set,OK);
    }


}
