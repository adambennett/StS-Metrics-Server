import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineRelicsComponent } from './machine-relics.component';

describe('MachineRelicsComponent', () => {
  let component: MachineRelicsComponent;
  let fixture: ComponentFixture<MachineRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
