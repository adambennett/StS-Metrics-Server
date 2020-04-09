import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohOnePotionsComponent } from './pharaoh-one-potions.component';

describe('PharaohOnePotionsComponent', () => {
  let component: PharaohOnePotionsComponent;
  let fixture: ComponentFixture<PharaohOnePotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohOnePotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohOnePotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
