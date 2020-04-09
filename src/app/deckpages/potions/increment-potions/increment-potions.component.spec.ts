import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncrementPotionsComponent } from './increment-potions.component';

describe('IncrementPotionsComponent', () => {
  let component: IncrementPotionsComponent;
  let fixture: ComponentFixture<IncrementPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncrementPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncrementPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
