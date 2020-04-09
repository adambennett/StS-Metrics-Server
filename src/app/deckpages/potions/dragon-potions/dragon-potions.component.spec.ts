import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DragonPotionsComponent } from './dragon-potions.component';

describe('DragonPotionsComponent', () => {
  let component: DragonPotionsComponent;
  let fixture: ComponentFixture<DragonPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DragonPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DragonPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
