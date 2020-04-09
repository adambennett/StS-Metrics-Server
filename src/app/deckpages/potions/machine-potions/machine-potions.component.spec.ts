import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachinePotionsComponent } from './machine-potions.component';

describe('MachinePotionsComponent', () => {
  let component: MachinePotionsComponent;
  let fixture: ComponentFixture<MachinePotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachinePotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachinePotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
