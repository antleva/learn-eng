import { Component, OnInit, Input } from '@angular/core';
import { PhraseModel } from '../phrase-model';
import { Router } from '@angular/router';
@Component({
  selector: 'app-phrases',
  templateUrl: './phrases.component.html',
  styleUrls: ['./phrases.component.css']
})
export class PhrasesComponent implements OnInit {

  @Input() phrases: PhraseModel[];

  constructor(private router: Router) { }

  ngOnInit(): void {}

  goToPhrase(id: number): void {this.router.navigateByUrl('/view-phrase/' + id);}
}
