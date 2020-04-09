import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StandardPotionsComponent } from './standard-potions.component';

describe('StandardPotionsComponent', () => {
  let component: StandardPotionsComponent;
  let fixture: ComponentFixture<StandardPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StandardPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StandardPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
