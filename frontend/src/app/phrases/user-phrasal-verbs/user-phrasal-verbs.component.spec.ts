import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPhrasalVerbsComponent } from './user-phrasal-verbs.component';

describe('UserPhrasalVerbsComponent', () => {
  let component: UserPhrasalVerbsComponent;
  let fixture: ComponentFixture<UserPhrasalVerbsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserPhrasalVerbsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPhrasalVerbsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
