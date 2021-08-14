import { Component, OnInit } from '@angular/core';
import { PhraseModel } from '../phrase-model';
import { PhraseService } from '../phrase-service/phrase.service';


@Component({
  selector: 'app-allphrases',
  templateUrl: './allphrases.component.html',
  styleUrls: ['./allphrases.component.css']
})
export class AllphrasesComponent implements OnInit {

  phrases: Array<PhraseModel> = [];
  constructor(private phraseService: PhraseService) {
      this.phraseService.getAllPhrases().subscribe(phrase => {this.phrases = phrase;});
  }

  ngOnInit(): void {}
}
