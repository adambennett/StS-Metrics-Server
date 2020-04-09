import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedThreePotionsComponent } from './ascended-three-potions.component';

describe('AscendedThreePotionsComponent', () => {
  let component: AscendedThreePotionsComponent;
  let fixture: ComponentFixture<AscendedThreePotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedThreePotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedThreePotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
