package com.myproject.learneng.util;

import com.myproject.learneng.dao.VideoRepository;
import com.myproject.learneng.domain.Phrase;
import com.myproject.learneng.domain.PhraseType;
import com.myproject.learneng.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public class PhrasesPopulator implements ApplicationListener<ContextRefreshedEvent> {

    private PhraseService phraseService;
    private VideoRepository videoRepository;

    @Autowired
    public PhrasesPopulator(PhraseService phraseService, VideoRepository videoRepository) {
        this.phraseService = phraseService;
        this.videoRepository = videoRepository;
    }

    public void populate() throws IOException {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<String>> callables = new ArrayList<>();
        InputStream inputStream = new ClassPathResource("phrases.txt").getInputStream();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                String[] strings = s.split(" : ");
                callables.add(() -> parseAndSavePhrases(strings));
            }

            List<Future<String>> futures = executor.invokeAll(callables);
            for (Future<String> f : futures) {
                f.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }


    public String parseAndSavePhrases(String[] strings) {
        String description = strings[0];
        String translation = strings[1];
        PhraseType phraseType = PhraseType.valueOf(strings[2]);
        Phrase phrase = new Phrase(phraseType, description, translation);
        phraseService.create(phrase);
        try {
            videoRepository.addVideo(phrase.getDescription(), VideoLoader.parseVideo(phrase.getDescription(), 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "phrase is added";
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            populate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
