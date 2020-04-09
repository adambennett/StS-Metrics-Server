import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncrementNeowComponent } from './increment-neow.component';

describe('IncrementNeowComponent', () => {
  let component: IncrementNeowComponent;
  let fixture: ComponentFixture<IncrementNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncrementNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncrementNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
