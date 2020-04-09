import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncrementCardsComponent } from './increment-cards.component';

describe('IncrementCardsComponent', () => {
  let component: IncrementCardsComponent;
  let fixture: ComponentFixture<IncrementCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncrementCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncrementCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
