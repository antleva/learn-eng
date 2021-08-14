import { Component, OnInit } from '@angular/core';
import { PhraseModel } from '../phrase-model';
import { ProfileService } from 'src/app/userprofile/profile-service/profile.service';

@Component({
  selector: 'app-user-phrasal-verbs',
  templateUrl: './user-phrasal-verbs.component.html',
  styleUrls: ['./user-phrasal-verbs.component.css']
})
export class UserPhrasalVerbsComponent implements OnInit {

    phrases: Array<PhraseModel> = [];

  constructor(private profileService: ProfileService) {
    this.profileService.getUsersPhrasalVerbs().subscribe(phrase => {this.phrases = phrase;});
  }

  ngOnInit(): void {}
}
