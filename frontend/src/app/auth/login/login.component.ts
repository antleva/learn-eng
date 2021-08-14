import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { LoginRequestPayload } from './login-request.payload';
import { AuthService } from '../shared/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    loginForm: FormGroup;
    loginRequestPayload: LoginRequestPayload;
    isError: boolean;

    constructor(private authService: AuthService, private activatedRoute: ActivatedRoute, private router: Router) {}

    ngOnInit(){
       this.loginRequestPayload = {username: '', password: ''};
       this.loginForm = new FormGroup({
        username: new FormControl('', Validators.required),
        password: new FormControl('', Validators.required)
      });
    }

    get form(){return this.loginForm.controls;}

    login() {
      this.loginRequestPayload.username = this.loginForm.get('username')?.value;
      this.loginRequestPayload.password = this.loginForm.get('password')?.value;

      this.authService.login(this.loginRequestPayload).subscribe(data => {
        this.isError = false;
        this.router.navigateByUrl('/home');
      }, error => {
        this.isError = true;
        throwError(error);
      });
    }
}
