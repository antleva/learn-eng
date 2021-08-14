import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddToUserslistComponent } from './add-to-userslist.component';

describe('AddToUserslistComponent', () => {
  let component: AddToUserslistComponent;
  let fixture: ComponentFixture<AddToUserslistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddToUserslistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddToUserslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
