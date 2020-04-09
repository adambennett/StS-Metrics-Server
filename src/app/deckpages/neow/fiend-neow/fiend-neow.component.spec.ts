import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiendNeowComponent } from './fiend-neow.component';

describe('FiendNeowComponent', () => {
  let component: FiendNeowComponent;
  let fixture: ComponentFixture<FiendNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiendNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiendNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
