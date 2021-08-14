import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserIdiomsComponent } from './user-idioms.component';

describe('UserIdiomsComponent', () => {
  let component: UserIdiomsComponent;
  let fixture: ComponentFixture<UserIdiomsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserIdiomsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserIdiomsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
