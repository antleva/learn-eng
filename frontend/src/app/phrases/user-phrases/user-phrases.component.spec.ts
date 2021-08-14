import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPhrasesComponent } from './user-phrases.component';

describe('UserPhrasesComponent', () => {
  let component: UserPhrasesComponent;
  let fixture: ComponentFixture<UserPhrasesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserPhrasesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPhrasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
