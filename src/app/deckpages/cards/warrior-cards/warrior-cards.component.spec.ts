import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarriorCardsComponent } from './warrior-cards.component';

describe('WarriorCardsComponent', () => {
  let component: WarriorCardsComponent;
  let fixture: ComponentFixture<WarriorCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarriorCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarriorCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
