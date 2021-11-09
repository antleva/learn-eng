import { Component, OnInit } from '@angular/core';
import { PhraseModel } from '../phrase-model';
import { ProfileService } from 'src/app/userprofile/profile-service/profile.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-phrasal-verbs',
  templateUrl: './user-phrasal-verbs.component.html',
  styleUrls: ['./user-phrasal-verbs.component.css']
})
export class UserPhrasalVerbsComponent implements OnInit {

    phrases: Array<PhraseModel> = [];

  constructor(private profileService: ProfileService, private router: Router) {
    this.profileService.getUsersPhrasalVerbs().subscribe(phrase => {this.phrases = phrase;});
  }

  goToPhrase(id: number): void {this.router.navigateByUrl('/view-phrase/' + id);}

  deleteFromMyCollection(id: number): void{
       this.profileService.deletePhraseFromCollection(id).subscribe(data => {this.router.navigate(['/user-phrases'])});
  }
  ngOnInit(): void {}
}
