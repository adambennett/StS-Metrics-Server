import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohThreeCardsComponent } from './pharaoh-three-cards.component';

describe('PharaohThreeCardsComponent', () => {
  let component: PharaohThreeCardsComponent;
  let fixture: ComponentFixture<PharaohThreeCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohThreeCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohThreeCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
