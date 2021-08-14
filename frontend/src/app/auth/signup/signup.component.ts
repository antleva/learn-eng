import { Component, OnInit} from '@angular/core';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { SignupRequestPayload } from './signup-request.payload';
import { AuthService } from '../shared/auth.service';
import { Router } from '@angular/router';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{
    signupRequestPayload: SignupRequestPayload;
    signupForm: FormGroup;
    isError: boolean;

    constructor(private authService: AuthService, private router: Router) {}

   ngOnInit(){
      this.signupRequestPayload = {username: '', password: ''};
       this.signupForm = new FormGroup({
         username: new FormControl('', Validators.required),
         password: new FormControl('', Validators.required)
       });
     }

   get form(){return this.signupForm.controls;}

   signup(){
        this.signupRequestPayload.username = this.signupForm.get('username')?.value;
        this.signupRequestPayload.password = this.signupForm.get('password')?.value;
        this.authService.signup(this.signupRequestPayload)
          .subscribe(data => {
            this.isError = false;
            this.router.navigate(['/login'],
              { queryParams: { registered: 'true' } });
          }, error => {console.log(error);
            this.isError = true;
            throwError(error);
          });
   }
}






