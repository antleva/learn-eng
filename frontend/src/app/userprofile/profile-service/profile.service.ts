import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProfileModel } from '../profile-model';
import { UpdateProfilePayload } from '../update-profile-payload';
import { PhraseModel } from 'src/app/phrases/phrase-model';
import { ResultModel } from 'src/app/userprofile/result-model';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

    constructor(private http: HttpClient) { }

    getProfile(): Observable<ProfileModel> {return this.http.get<ProfileModel>('http://localhost:8080/profile');}

    getResults(): Observable<Array<string>>{return this.http.get<Array<string>>('http://localhost:8080/profile/results');}

    getUsersPhrases(): Observable<Array<PhraseModel>>{
       return this.http.get<Array<PhraseModel>>('http://localhost:8080/profile/allUsersPhrases');
    }

    getUsersIdioms(): Observable<Array<PhraseModel>> {
       return this.http.get<Array<PhraseModel>>('http://localhost:8080/profile/allUsersIdioms');
    }
    getUsersPhrasalVerbs(): Observable<Array<PhraseModel>> {
       return this.http.get<Array<PhraseModel>>('http://localhost:8080/profile/allUsersPhrasalVerbs');
    }
    updateProfile(id: number, updateProfilePayload: UpdateProfilePayload): Observable<any> {
       return this.http.post('http://localhost:8080/users/'+id+'/update', updateProfilePayload);
    }

    learnPhrases(): Observable<Array<PhraseModel>> {
       return this.http.get<Array<PhraseModel>>('http://localhost:8080/profile/learnRandomPhrases');
    }

    addResult(resultModel: ResultModel): Observable<any> {
       return this.http.post('http://localhost:8080/profile/addResult', resultModel);
    }

    deletePhraseFromCollection(id: number): Observable<any> {
      return this.http.delete('http://localhost:8080/profile/'+id+'/deletePhrase');
    }
}
