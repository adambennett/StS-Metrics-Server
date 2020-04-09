import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsectPotionsComponent } from './insect-potions.component';

describe('InsectPotionsComponent', () => {
  let component: InsectPotionsComponent;
  let fixture: ComponentFixture<InsectPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsectPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsectPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
