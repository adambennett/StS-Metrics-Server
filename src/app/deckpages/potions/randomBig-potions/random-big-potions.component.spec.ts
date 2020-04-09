import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomBigPotionsComponent } from './random-big-potions.component';

describe('RandomBigPotionsComponent', () => {
  let component: RandomBigPotionsComponent;
  let fixture: ComponentFixture<RandomBigPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RandomBigPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomBigPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
