import { Component, OnInit } from '@angular/core';
import { PhraseService } from 'src/app/phrases/phrase-service/phrase.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PhraseModel } from 'src/app/phrases/phrase-model';
import { throwError } from 'rxjs';
import { FormGroup, FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-update-phrases',
  templateUrl: './update-phrases.component.html',
  styleUrls: ['./update-phrases.component.css']
})
export class UpdatePhrasesComponent implements OnInit {
    phraseId: number;
    phrase: PhraseModel;
    updatePhraseForm: FormGroup;
    index: number;

  constructor(private phraseService: PhraseService, private activateRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.index = 1;
    this.phraseId = this.activateRoute.snapshot.params.id;
    this.phraseService.getPhrase(this.phraseId).subscribe(data => {this.phrase = data;}, error => {throwError(error);});
    this.updatePhraseForm = new FormGroup({
                 type: new FormControl('', Validators.required),
                 description: new FormControl('', Validators.required),
                 translation: new FormControl('', Validators.required)
    });
  }

  get form(){return this.updatePhraseForm.controls;}

  update(){
    this.phrase.type = this.updatePhraseForm.get('type')?.value;
    this.phrase.translation = this.updatePhraseForm.get('translation')?.value;
    this.phrase.description = this.updatePhraseForm.get('description')?.value;
    this.phraseService.updatePhrase(this.phraseId, this.phrase).subscribe(data => {this.phrase=data;});
  }

  changeVideo(){
    this.index++;
    this.phraseService.changeVideo(this.phrase.description,this.index).subscribe(data => {this.phrase=data;});
  }

}
