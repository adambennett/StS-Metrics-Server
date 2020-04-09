import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiendPotionsComponent } from './fiend-potions.component';

describe('FiendPotionsComponent', () => {
  let component: FiendPotionsComponent;
  let fixture: ComponentFixture<FiendPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiendPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiendPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
