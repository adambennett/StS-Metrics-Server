import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineNeowComponent } from './machine-neow.component';

describe('MachineNeowComponent', () => {
  let component: MachineNeowComponent;
  let fixture: ComponentFixture<MachineNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
