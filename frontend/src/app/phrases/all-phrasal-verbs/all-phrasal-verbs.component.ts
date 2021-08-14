import { Component, OnInit } from '@angular/core';
import { PhraseModel } from '../phrase-model';
import { PhraseService } from '../phrase-service/phrase.service';

@Component({
  selector: 'app-all-phrasal-verbs',
  templateUrl: './all-phrasal-verbs.component.html',
  styleUrls: ['./all-phrasal-verbs.component.css']
})
export class AllPhrasalVerbsComponent implements OnInit {

  phrases: Array<PhraseModel> = [];
  constructor(private phraseService: PhraseService) {
        this.phraseService.getAllPhrasalVerbs().subscribe(phrase => {this.phrases = phrase;});
  }

  ngOnInit(): void {}
}
