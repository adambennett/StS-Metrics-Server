import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomBigCardsComponent } from './random-big-cards.component';

describe('RandomBigCardsComponent', () => {
  let component: RandomBigCardsComponent;
  let fixture: ComponentFixture<RandomBigCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RandomBigCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomBigCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
