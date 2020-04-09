import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiendInfoComponent } from './fiend-info.component';

describe('FiendInfoComponent', () => {
  let component: FiendInfoComponent;
  let fixture: ComponentFixture<FiendInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiendInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiendInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
