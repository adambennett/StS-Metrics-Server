import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineCardsComponent } from './machine-cards.component';

describe('MachineCardsComponent', () => {
  let component: MachineCardsComponent;
  let fixture: ComponentFixture<MachineCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
