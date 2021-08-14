import { Component, OnInit } from '@angular/core';
import { PhraseService } from '../phrase-service/phrase.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PhraseModel } from 'src/app/phrases/phrase-model';
import { throwError } from 'rxjs';

@Component({
  selector: 'app-add-to-userslist',
  templateUrl: './add-to-userslist.component.html',
  styleUrls: ['./add-to-userslist.component.css']
})
export class AddToUserslistComponent implements OnInit {

  phraseId: number;
  phrase: PhraseModel;

  constructor(private phraseService: PhraseService,private activateRoute: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
      this.phraseId = this.activateRoute.snapshot.params.id;
      this.phraseService.getPhrase(this.phraseId).subscribe(data => {this.phrase = data;}, error => {throwError(error);});
  }
  add(){
    this.phraseService.addPhraseToUsersList(this.phraseId).subscribe(data => {this.router.navigate(['/user-phrases'])})
  }


}
