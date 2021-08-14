import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LearnPhrasesComponent } from './learn-phrases.component';

describe('LearnPhrasesComponent', () => {
  let component: LearnPhrasesComponent;
  let fixture: ComponentFixture<LearnPhrasesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LearnPhrasesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LearnPhrasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
