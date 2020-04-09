import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsectNeowComponent } from './insect-neow.component';

describe('InsectNeowComponent', () => {
  let component: InsectNeowComponent;
  let fixture: ComponentFixture<InsectNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsectNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsectNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
