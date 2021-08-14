import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePhrasesComponent } from './create-phrases.component';

describe('CreatePhrasesComponent', () => {
  let component: CreatePhrasesComponent;
  let fixture: ComponentFixture<CreatePhrasesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePhrasesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePhrasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
