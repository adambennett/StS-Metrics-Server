import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlantCardsComponent } from './plant-cards.component';

describe('PlantCardsComponent', () => {
  let component: PlantCardsComponent;
  let fixture: ComponentFixture<PlantCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlantCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlantCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
