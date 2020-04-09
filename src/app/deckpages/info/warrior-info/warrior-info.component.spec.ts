import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WarriorInfoComponent } from './warrior-info.component';

describe('WarriorInfoComponent', () => {
  let component: WarriorInfoComponent;
  let fixture: ComponentFixture<WarriorInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WarriorInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WarriorInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
