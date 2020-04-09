import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarriorNeowComponent } from './warrior-neow.component';

describe('WarriorNeowComponent', () => {
  let component: WarriorNeowComponent;
  let fixture: ComponentFixture<WarriorNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarriorNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarriorNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
