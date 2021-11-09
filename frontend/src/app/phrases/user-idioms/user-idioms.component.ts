import { Component, OnInit } from '@angular/core';
import { PhraseModel } from '../phrase-model';
import { ProfileService } from 'src/app/userprofile/profile-service/profile.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-idioms',
  templateUrl: './user-idioms.component.html',
  styleUrls: ['./user-idioms.component.css']
})
export class UserIdiomsComponent implements OnInit {

    phrases: Array<PhraseModel> = [];

  constructor(private profileService: ProfileService, private router: Router) {
    this.profileService.getUsersIdioms().subscribe(phrase => {this.phrases = phrase;});
  }
  goToPhrase(id: number): void {this.router.navigateByUrl('/view-phrase/' + id);}

  deleteFromMyCollection(id: number): void{
       this.profileService.deletePhraseFromCollection(id).subscribe(data => {this.router.navigate(['/user-phrases'])});
  }

  ngOnInit(): void {}
}
