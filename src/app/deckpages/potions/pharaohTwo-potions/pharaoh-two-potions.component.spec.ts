import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohTwoPotionsComponent } from './pharaoh-two-potions.component';

describe('PharaohTwoPotionsComponent', () => {
  let component: PharaohTwoPotionsComponent;
  let fixture: ComponentFixture<PharaohTwoPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohTwoPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohTwoPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
