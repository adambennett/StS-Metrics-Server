import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomBigNeowComponent } from './random-big-neow.component';

describe('RandomBigNeowComponent', () => {
  let component: RandomBigNeowComponent;
  let fixture: ComponentFixture<RandomBigNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RandomBigNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomBigNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
