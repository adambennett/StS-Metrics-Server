import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomSmallNeowComponent } from './random-small-neow.component';

describe('RandomSmallNeowComponent', () => {
  let component: RandomSmallNeowComponent;
  let fixture: ComponentFixture<RandomSmallNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RandomSmallNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomSmallNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
