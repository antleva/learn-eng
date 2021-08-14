import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../profile-service/profile.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfileModel } from '../profile-model';
import { UpdateProfilePayload } from '../update-profile-payload';
import { throwError } from 'rxjs';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { LocalStorageService } from 'ngx-webstorage';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {
    profilePayload: UpdateProfilePayload;
    profile: ProfileModel;
    profileId: number;
    updateProfileForm: FormGroup;
  constructor(private profileService: ProfileService, private activateRoute: ActivatedRoute, private router: Router,private localStorage: LocalStorageService) {}

  ngOnInit(): void {
    this.profileId = this.activateRoute.snapshot.params.id;
    this.profileService.getProfile().subscribe(data => {this.profile = data;}, error => {throwError(error);});
    this.updateProfileForm = new FormGroup({
                     name: new FormControl('', Validators.required),
                     password: new FormControl('', Validators.required)
    });
  }

  get form(){return this.updateProfileForm.controls;}

  update(){
     this.localStorage.clear('authenticationtoken');
     this.localStorage.clear('username');
     this.localStorage.clear('refreshtoken');
     this.localStorage.clear('expiresat');
     this.profileService.updateProfile(this.profileId, new UpdateProfilePayload({username: this.updateProfileForm.get('name')?.value,
                                                                                 password: this.updateProfileForm.get('password')?.value}))
     .subscribe(data => {this.router.navigate(['/login'])});
  }
}
