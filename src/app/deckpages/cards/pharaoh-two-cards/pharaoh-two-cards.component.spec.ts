import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohTwoCardsComponent } from './pharaoh-two-cards.component';

describe('PharaohTwoCardsComponent', () => {
  let component: PharaohTwoCardsComponent;
  let fixture: ComponentFixture<PharaohTwoCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohTwoCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohTwoCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
