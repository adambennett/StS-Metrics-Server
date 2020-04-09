import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlantNeowComponent } from './plant-neow.component';

describe('PlantNeowComponent', () => {
  let component: PlantNeowComponent;
  let fixture: ComponentFixture<PlantNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlantNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlantNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
