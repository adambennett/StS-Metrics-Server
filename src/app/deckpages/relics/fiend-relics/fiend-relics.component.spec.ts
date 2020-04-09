import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiendRelicsComponent } from './fiend-relics.component';

describe('FiendRelicsComponent', () => {
  let component: FiendRelicsComponent;
  let fixture: ComponentFixture<FiendRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiendRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiendRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
