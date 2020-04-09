import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlantPotionsComponent } from './plant-potions.component';

describe('PlantPotionsComponent', () => {
  let component: PlantPotionsComponent;
  let fixture: ComponentFixture<PlantPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlantPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlantPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
