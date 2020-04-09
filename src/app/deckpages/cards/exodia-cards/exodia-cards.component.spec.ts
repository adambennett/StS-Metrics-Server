import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExodiaCardsComponent } from './exodia-cards.component';

describe('ExodiaCardsComponent', () => {
  let component: ExodiaCardsComponent;
  let fixture: ComponentFixture<ExodiaCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExodiaCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExodiaCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
