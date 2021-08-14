import { Component, OnInit } from '@angular/core';
import { PhraseService } from 'src/app/phrases/phrase-service/phrase.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PhraseModel } from 'src/app/phrases/phrase-model';
import { throwError } from 'rxjs';
import { FormGroup, FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-create-phrases',
  templateUrl: './create-phrases.component.html',
  styleUrls: ['./create-phrases.component.css']
})
export class CreatePhrasesComponent implements OnInit {

  phrase: PhraseModel;
  createPhraseForm: FormGroup;
  phrases: Array<PhraseModel> = [];

  constructor(private phraseService: PhraseService, private activateRoute: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
     this.createPhraseForm = new FormGroup({
                      type: new FormControl('', Validators.required),
                      description: new FormControl('', Validators.required),
                      translation: new FormControl('', Validators.required)
     });
  }
  get form(){return this.createPhraseForm.controls;}

  create(){
     this.phrase = new PhraseModel({type: this.createPhraseForm.get('type')?.value,
                                    translation: this.createPhraseForm.get('translation')?.value,
                                    description: this.createPhraseForm.get('description')?.value});
     this.phraseService.createPhrase(this.phrase).subscribe(data => {this.router.navigate(['/all-phrases'])});
  }
}
