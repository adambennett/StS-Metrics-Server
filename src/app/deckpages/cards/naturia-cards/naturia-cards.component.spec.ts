import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NaturiaCardsComponent } from './naturia-cards.component';

describe('NaturiaCardsComponent', () => {
  let component: NaturiaCardsComponent;
  let fixture: ComponentFixture<NaturiaCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NaturiaCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NaturiaCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
