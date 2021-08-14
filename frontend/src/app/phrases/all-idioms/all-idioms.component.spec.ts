import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllIdiomsComponent } from './all-idioms.component';

describe('AllIdiomsComponent', () => {
  let component: AllIdiomsComponent;
  let fixture: ComponentFixture<AllIdiomsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllIdiomsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllIdiomsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
