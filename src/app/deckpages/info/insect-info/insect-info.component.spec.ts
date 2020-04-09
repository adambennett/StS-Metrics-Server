import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsectInfoComponent } from './insect-info.component';

describe('InsectInfoComponent', () => {
  let component: InsectInfoComponent;
  let fixture: ComponentFixture<InsectInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsectInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsectInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
