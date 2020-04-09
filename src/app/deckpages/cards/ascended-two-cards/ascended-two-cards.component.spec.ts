import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedTwoCardsComponent } from './ascended-two-cards.component';

describe('AscendedTwoCardsComponent', () => {
  let component: AscendedTwoCardsComponent;
  let fixture: ComponentFixture<AscendedTwoCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedTwoCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedTwoCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
