import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohFivePotionsComponent } from './pharaoh-five-potions.component';

describe('PharaohFivePotionsComponent', () => {
  let component: PharaohFivePotionsComponent;
  let fixture: ComponentFixture<PharaohFivePotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohFivePotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohFivePotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
