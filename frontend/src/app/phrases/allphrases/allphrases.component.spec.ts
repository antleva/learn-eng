import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllphrasesComponent } from './allphrases.component';

describe('AllphrasesComponent', () => {
  let component: AllphrasesComponent;
  let fixture: ComponentFixture<AllphrasesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllphrasesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllphrasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
