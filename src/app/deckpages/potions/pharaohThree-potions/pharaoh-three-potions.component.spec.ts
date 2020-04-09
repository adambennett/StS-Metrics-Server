import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohThreePotionsComponent } from './pharaoh-three-potions.component';

describe('PharaohThreePotionsComponent', () => {
  let component: PharaohThreePotionsComponent;
  let fixture: ComponentFixture<PharaohThreePotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohThreePotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohThreePotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
