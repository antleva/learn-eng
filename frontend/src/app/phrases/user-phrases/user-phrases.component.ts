import { Component, OnInit } from '@angular/core';
import { PhraseModel } from '../phrase-model';
import { ProfileService } from 'src/app/userprofile/profile-service/profile.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PhraseService } from '../phrase-service/phrase.service';

@Component({
  selector: 'app-user-phrases',
  templateUrl: './user-phrases.component.html',
  styleUrls: ['./user-phrases.component.css']
})
export class UserPhrasesComponent implements OnInit {
    id: number;
    phrases: Array<PhraseModel> = [];
  constructor(private profileService: ProfileService, private router: Router,
              private phraseService: PhraseService, private activateRoute: ActivatedRoute) {
    this.profileService.getUsersPhrases().subscribe(phrase => {this.phrases = phrase;});
  }

  ngOnInit(): void {}

  goToPhrase(id: number): void {this.router.navigateByUrl('/view-phrase/' + id);}

  deleteFromMyCollection(id: number): void{
     this.profileService.deletePhraseFromCollection(id).subscribe(data => {this.router.navigate(['/user-phrases'])});
  }

}
