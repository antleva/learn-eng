import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth/shared/auth.service';
import { Router } from '@angular/router';
import { PhraseModel } from '../phrases/phrase-model';
import { PhraseService } from '../phrases/phrase-service/phrase.service';
import { ProfileService } from 'src/app/userprofile/profile-service/profile.service';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit{
    isLoggedIn: boolean;
    username: string;
    phrases: Array<PhraseModel> = [];

  constructor(private authService: AuthService, private router: Router,
              private phraseService: PhraseService, private profileService: ProfileService) {}
  ngOnInit(){
    this.authService.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
    this.authService.username.subscribe((data: string) => this.username = data);
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUserName();
    if(this.isLoggedIn==false){
        this.router.navigateByUrl('/login');
    }
  }

  goToUserProfile() {this.router.navigateByUrl('/profile');}

  logout() {
        this.authService.logout();
        this.isLoggedIn = false;
        this.router.navigateByUrl('/home');
  }

  getAllPhrases(){this.router.navigateByUrl('/all-phrases');}

  getPhrases(){this.router.navigateByUrl('/user-phrases');}

  getUserPhrasalVerbs(){this.router.navigateByUrl('/user-phrasal-verbs');}

  getUserIdioms(){this.router.navigateByUrl('/user-idioms');}

  getAllPhrasalVerbs(){this.router.navigateByUrl('/all-phrasal-verbs');}

  getAllIdioms(){this.router.navigateByUrl('/all-idioms');}

  learnRandomPhrases(){this.router.navigateByUrl('/learn-random-phrases');}

  createPhrase(){this.router.navigateByUrl('/create-phrase');}

  getUsers(){this.router.navigateByUrl('/users');}
}
