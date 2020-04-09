import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedOnePotionsComponent } from './ascended-one-potions.component';

describe('AscendedOnePotionsComponent', () => {
  let component: AscendedOnePotionsComponent;
  let fixture: ComponentFixture<AscendedOnePotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedOnePotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedOnePotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
