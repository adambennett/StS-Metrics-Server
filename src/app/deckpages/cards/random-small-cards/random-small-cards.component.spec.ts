import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomSmallCardsComponent } from './random-small-cards.component';

describe('RandomSmallCardsComponent', () => {
  let component: RandomSmallCardsComponent;
  let fixture: ComponentFixture<RandomSmallCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RandomSmallCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomSmallCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
