import { Component, OnInit } from '@angular/core';
import { PhraseModel } from '../phrase-model';
import { PhraseService } from '../phrase-service/phrase.service';

@Component({
  selector: 'app-all-idioms',
  templateUrl: './all-idioms.component.html',
  styleUrls: ['./all-idioms.component.css']
})
export class AllIdiomsComponent implements OnInit {

  phrases: Array<PhraseModel> = [];
  constructor(private phraseService: PhraseService) {
          this.phraseService.getAllIdioms().subscribe(phrase => {this.phrases = phrase;});
  }

  ngOnInit(): void {}
}
