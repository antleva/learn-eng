import { Component, OnInit } from '@angular/core';
import { PhraseService } from 'src/app/phrases/phrase-service/phrase.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PhraseModel } from 'src/app/phrases/phrase-model';
import { ResultModel } from 'src/app/userprofile/result-model';
import { throwError } from 'rxjs';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { ProfileService } from 'src/app/userprofile/profile-service/profile.service';
import {Injectable} from '@angular/core';

@Component({
  selector: 'app-learn-phrases',
  templateUrl: './learn-phrases.component.html',
  styleUrls: ['./learn-phrases.component.css']
})

@Injectable()
export class LearnPhrasesComponent implements OnInit {
    phrase: PhraseModel;
    result: ResultModel;
    phrases: Array<PhraseModel> = [];
    quantityForm: FormGroup;
    checkForm: FormGroup;
    count: number;
    quantity: number;
    translation: string;
    rightAnswers: number;
    wrongAnswers: number;
    totalPhrases: number;

  constructor(private profileService: ProfileService, private activateRoute: ActivatedRoute, private router: Router) {}
  ngOnInit(): void {
    this.checkForm = new FormGroup({translation: new FormControl('', Validators.required)});
    this.quantityForm = new FormGroup({quantity: new FormControl('', Validators.required)});
    this.profileService.learnPhrases().subscribe(phrase => {this.phrases = phrase;});
    this.totalPhrases = this.phrases.length;
  }

  learn(){
    this.count=0;
    this.wrongAnswers=0;
    this.rightAnswers=0;
    this.phrase=this.phrases[0];
  }

  check(){
    this.translation = this.checkForm.get('translation')?.value;
    if(this.translation==this.phrases[this.count].translation){
        this.rightAnswers++;
    }else{
        this.wrongAnswers++;
    }
    ++this.count;
    this.phrase=this.phrases[this.count];
  }

  finish(){
    this.profileService.addResult(new ResultModel({
                                            totalPhrases: this.phrases.length,
                                            wrongAnswers: this.wrongAnswers,
                                            rightAnswers: this.rightAnswers}))
    .subscribe(data => {this.router.navigate(['/user-phrases'])});
  }
}
