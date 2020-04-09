import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlantRelicsComponent } from './plant-relics.component';

describe('PlantRelicsComponent', () => {
  let component: PlantRelicsComponent;
  let fixture: ComponentFixture<PlantRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlantRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlantRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
