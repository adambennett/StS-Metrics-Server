import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomSmallPotionsComponent } from './random-small-potions.component';

describe('RandomSmallPotionsComponent', () => {
  let component: RandomSmallPotionsComponent;
  let fixture: ComponentFixture<RandomSmallPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RandomSmallPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomSmallPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
