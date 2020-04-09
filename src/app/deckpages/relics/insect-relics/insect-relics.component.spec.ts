import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsectRelicsComponent } from './insect-relics.component';

describe('InsectRelicsComponent', () => {
  let component: InsectRelicsComponent;
  let fixture: ComponentFixture<InsectRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsectRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsectRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
