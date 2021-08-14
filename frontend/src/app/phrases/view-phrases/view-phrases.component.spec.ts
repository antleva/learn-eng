import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPhrasesComponent } from './view-phrases.component';

describe('ViewPhrasesComponent', () => {
  let component: ViewPhrasesComponent;
  let fixture: ComponentFixture<ViewPhrasesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPhrasesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPhrasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
