import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ViewPhrasesComponent } from './phrases/view-phrases/view-phrases.component';
import { ViewProfileComponent } from './userprofile/view-profile/view-profile.component';
import { UpdatePhrasesComponent } from './phrases/update-phrases/update-phrases.component';
import { AllphrasesComponent } from './phrases/allphrases/allphrases.component';
import { UserPhrasesComponent } from './phrases/user-phrases/user-phrases.component';
import { UserIdiomsComponent } from './phrases/user-idioms/user-idioms.component';
import { UserPhrasalVerbsComponent } from './phrases/user-phrasal-verbs/user-phrasal-verbs.component';
import { AllPhrasalVerbsComponent } from './phrases/all-phrasal-verbs/all-phrasal-verbs.component';
import { AllIdiomsComponent } from './phrases/all-idioms/all-idioms.component';
import { AddToUserslistComponent } from './phrases/add-to-userslist/add-to-userslist.component';
import { LearnPhrasesComponent } from './phrases/learn-phrases/learn-phrases.component';
import { CreatePhrasesComponent } from './phrases/create-phrases/create-phrases.component';
import { UpdateProfileComponent } from './userprofile/update-profile/update-profile.component';
import { AllUsersComponent } from './admin/all-users/all-users.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
    {path: 'sign-up', component: SignupComponent},
    {path: 'login', component: LoginComponent},
    {path: 'home', component: MainpageComponent, canActivate: [AuthGuard]},
    {path: 'view-phrase/:id', component: ViewPhrasesComponent},
    {path: 'update/:id', component: UpdatePhrasesComponent},
    {path: 'profile', component: ViewProfileComponent, canActivate: [AuthGuard]},
    {path: 'all-phrases', component: AllphrasesComponent, canActivate: [AuthGuard]},
    {path: 'all-phrasal-verbs', component: AllPhrasalVerbsComponent, canActivate: [AuthGuard]},
    {path: 'all-idioms', component: AllIdiomsComponent, canActivate: [AuthGuard]},
    {path: 'user-phrases', component: UserPhrasesComponent, canActivate: [AuthGuard]},
    {path: 'user-idioms', component: UserIdiomsComponent, canActivate: [AuthGuard]},
    {path: 'user-phrasal-verbs', component: UserPhrasalVerbsComponent, canActivate: [AuthGuard]},
    {path: 'learn-random-phrases', component: LearnPhrasesComponent, canActivate: [AuthGuard]},
    {path: 'create-phrase', component: CreatePhrasesComponent, canActivate: [AuthGuard]},
    {path: 'update-profile/:id', component: UpdateProfileComponent, canActivate: [AuthGuard]},
    {path: 'users', component: AllUsersComponent, canActivate: [AuthGuard]}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

