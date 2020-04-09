import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedTwoPotionsComponent } from './ascended-two-potions.component';

describe('AscendedTwoPotionsComponent', () => {
  let component: AscendedTwoPotionsComponent;
  let fixture: ComponentFixture<AscendedTwoPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedTwoPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedTwoPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
