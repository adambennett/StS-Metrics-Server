import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllNeowComponent } from './all-neow.component';

describe('AllNeowComponent', () => {
  let component: AllNeowComponent;
  let fixture: ComponentFixture<AllNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
