import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllPotionsComponent } from './all-potions.component';

describe('AllPotionsComponent', () => {
  let component: AllPotionsComponent;
  let fixture: ComponentFixture<AllPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
