import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProfileModel } from 'src/app/userprofile/profile-model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<Array<ProfileModel>>{
      return this.http.get<Array<ProfileModel>>('http://localhost:8080/users');
  }

  deleteUser(id: string): Observable<Array<ProfileModel>>{
      return this.http.delete<Array<ProfileModel>>('http://localhost:8080/users/'+id+'/delete');
  }



}
