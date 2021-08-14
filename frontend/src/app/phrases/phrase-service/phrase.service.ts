import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PhraseModel } from '../phrase-model';
import { Observable } from 'rxjs';
import { UpdatePhrasePayload } from './update-phrase.payload';

@Injectable({
  providedIn: 'root'
})
export class PhraseService {

  constructor(private http: HttpClient) {}

  getAllPhrases(): Observable<Array<PhraseModel>> {
    return this.http.get<Array<PhraseModel>>('http://localhost:8080/phrase');
  }

  getAllPhrasalVerbs(): Observable<Array<PhraseModel>> {
      return this.http.get<Array<PhraseModel>>('http://localhost:8080/phrase/phrasalVerbs');
  }
  getAllIdioms(): Observable<Array<PhraseModel>> {
      return this.http.get<Array<PhraseModel>>('http://localhost:8080/phrase/idioms');
  }

  createPhrase(phrasePayload: PhraseModel): Observable<any> {
      return this.http.post('http://localhost:8080/phrase/create', phrasePayload);
  }

  updatePhrase(id: number, phrasePayload: PhraseModel): Observable<any> {
    return this.http.post('http://localhost:8080/phrase/'+ id +'/update', phrasePayload);
  }

  getPhrase(id: number): Observable<PhraseModel> {
    return this.http.get<PhraseModel>('http://localhost:8080/phrase/' + id);
  }

  getPhraseByType(name: string): Observable<PhraseModel[]> {
    return this.http.get<PhraseModel[]>('http://localhost:8080/phrase/' + name);
  }

  addPhraseToUsersList(id: number): Observable<any>{
    return this.http.get('http://localhost:8080/phrase/addToUsersList/'+ id);
  }

  changeVideo(description: string, index: number):Observable<any>{
    return this.http.get('http://localhost:8080/phrase/changeVideo/'+ description.replace(/ /g,'%20') +'/'+ index);
  }
}
