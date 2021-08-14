import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllPhrasalVerbsComponent } from './all-phrasal-verbs.component';

describe('AllPhrasalVerbsComponent', () => {
  let component: AllPhrasalVerbsComponent;
  let fixture: ComponentFixture<AllPhrasalVerbsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllPhrasalVerbsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllPhrasalVerbsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
