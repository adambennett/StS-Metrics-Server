import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohOneCardsComponent } from './pharaoh-one-cards.component';

describe('PharaohOneCardsComponent', () => {
  let component: PharaohOneCardsComponent;
  let fixture: ComponentFixture<PharaohOneCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohOneCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohOneCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
