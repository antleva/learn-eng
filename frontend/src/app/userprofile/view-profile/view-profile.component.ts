import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../profile-service/profile.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfileModel } from '../profile-model';
import { throwError } from 'rxjs';
import { PhraseModel } from 'src/app/phrases/phrase-model';

@Component({
  selector: 'app-view-profile',
  templateUrl: './view-profile.component.html',
  styleUrls: ['./view-profile.component.css']
})
export class ViewProfileComponent implements OnInit {

    profile: ProfileModel;
    roles: string[];
    results: string[];
    phrases: PhraseModel[];

  constructor(private profileService: ProfileService, private activateRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.profileService.getProfile().subscribe(data => {
    this.profile = data;
    this.roles = this.profile.roles;}, error => {throwError(error);});
  }

  update(): void {this.router.navigateByUrl('/update-profile/' + this.profile.id);}

  getResults(): void{this.profileService.getResults().subscribe(data => {this.results = data;},
    error => {throwError(error);});
  }
}
