import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { LocationStrategy, HashLocationStrategy} from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { PhrasesComponent } from './phrases/phrases/phrases.component';
import { TokenInterceptor } from './token-interceptor';
import { ViewPhrasesComponent } from './phrases/view-phrases/view-phrases.component';
import { UpdatePhrasesComponent } from './phrases/update-phrases/update-phrases.component';
import { CreatePhrasesComponent } from './phrases/create-phrases/create-phrases.component';
import { ViewProfileComponent } from './userprofile/view-profile/view-profile.component';
import { UpdateProfileComponent } from './userprofile/update-profile/update-profile.component';
import { UserPhrasesComponent } from './phrases/user-phrases/user-phrases.component';
import { UserPhrasalVerbsComponent } from './phrases/user-phrasal-verbs/user-phrasal-verbs.component';
import { UserIdiomsComponent } from './phrases/user-idioms/user-idioms.component';
import { AllphrasesComponent } from './phrases/allphrases/allphrases.component';
import { AllPhrasalVerbsComponent } from './phrases/all-phrasal-verbs/all-phrasal-verbs.component';
import { AllIdiomsComponent } from './phrases/all-idioms/all-idioms.component';
import { AddToUserslistComponent } from './phrases/add-to-userslist/add-to-userslist.component';
import { LearnPhrasesComponent } from './phrases/learn-phrases/learn-phrases.component';
import { AllUsersComponent } from './admin/all-users/all-users.component';

@NgModule({
  declarations: [
    AppComponent,
    MainpageComponent,
    SignupComponent,
    LoginComponent,
    PhrasesComponent,
    ViewPhrasesComponent,
    UpdatePhrasesComponent,
    CreatePhrasesComponent,
    ViewProfileComponent,
    UpdateProfileComponent,
    UserPhrasesComponent,
    UserPhrasalVerbsComponent,
    UserIdiomsComponent,
    AllphrasesComponent,
    AllPhrasalVerbsComponent,
    AllIdiomsComponent,
    AddToUserslistComponent,
    LearnPhrasesComponent,
    AllUsersComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot()
  ],
  providers: [
                 {
                   provide: HTTP_INTERCEPTORS,
                   useClass: TokenInterceptor,
                   multi: true
                 },
                 {
                   provide: LocationStrategy,
                   useClass: HashLocationStrategy
                 }


               ],
  bootstrap: [AppComponent]
})
export class AppModule { }
