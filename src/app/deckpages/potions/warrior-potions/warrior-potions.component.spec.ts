import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarriorPotionsComponent } from './warrior-potions.component';

describe('WarriorPotionsComponent', () => {
  let component: WarriorPotionsComponent;
  let fixture: ComponentFixture<WarriorPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarriorPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarriorPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
