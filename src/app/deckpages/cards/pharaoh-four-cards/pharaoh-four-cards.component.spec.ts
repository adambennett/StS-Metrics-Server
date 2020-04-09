import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohFourCardsComponent } from './pharaoh-four-cards.component';

describe('PharaohFourCardsComponent', () => {
  let component: PharaohFourCardsComponent;
  let fixture: ComponentFixture<PharaohFourCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohFourCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohFourCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
