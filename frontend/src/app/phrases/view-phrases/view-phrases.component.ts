import { Component, OnInit } from '@angular/core';
import { PhraseService } from 'src/app/phrases/phrase-service/phrase.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PhraseModel } from 'src/app/phrases/phrase-model';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-view-phrases',
  templateUrl: './view-phrases.component.html',
  styleUrls: ['./view-phrases.component.css']
})
export class ViewPhrasesComponent implements OnInit {

    phraseId: number;
    phrase: PhraseModel;

  constructor(private phraseService: PhraseService, private activateRoute: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.phraseId = this.activateRoute.snapshot.params.id;
    console.log(this.phraseId);
    this.phraseService.getPhrase(this.phraseId).subscribe(data => {this.phrase = data;}, error => {throwError(error);});
  }
  update(){this.router.navigate(['/update/'+this.phraseId])}

  addToMyList(){
     this.phraseService.addPhraseToUsersList(this.phraseId).subscribe(data => {this.router.navigate(['/user-phrases'])})
  }
}
