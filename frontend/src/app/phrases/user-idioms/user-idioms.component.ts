import { Component, OnInit } from '@angular/core';
import { PhraseModel } from '../phrase-model';
import { ProfileService } from 'src/app/userprofile/profile-service/profile.service';

@Component({
  selector: 'app-user-idioms',
  templateUrl: './user-idioms.component.html',
  styleUrls: ['./user-idioms.component.css']
})
export class UserIdiomsComponent implements OnInit {

    phrases: Array<PhraseModel> = [];

  constructor(private profileService: ProfileService) {
    this.profileService.getUsersIdioms().subscribe(phrase => {this.phrases = phrase;});
  }

  ngOnInit(): void {}
}
