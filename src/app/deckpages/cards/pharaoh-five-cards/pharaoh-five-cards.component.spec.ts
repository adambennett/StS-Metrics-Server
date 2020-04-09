import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohFiveCardsComponent } from './pharaoh-five-cards.component';

describe('PharaohFiveCardsComponent', () => {
  let component: PharaohFiveCardsComponent;
  let fixture: ComponentFixture<PharaohFiveCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohFiveCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohFiveCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
