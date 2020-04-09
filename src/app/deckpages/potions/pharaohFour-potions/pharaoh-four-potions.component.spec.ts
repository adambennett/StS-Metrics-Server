import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohFourPotionsComponent } from './pharaoh-four-potions.component';

describe('PharaohFourPotionsComponent', () => {
  let component: PharaohFourPotionsComponent;
  let fixture: ComponentFixture<PharaohFourPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohFourPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohFourPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
