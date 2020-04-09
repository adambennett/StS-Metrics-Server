import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomSmallInfoComponent } from './random-small-info.component';

describe('RandomSmallInfoComponent', () => {
  let component: RandomSmallInfoComponent;
  let fixture: ComponentFixture<RandomSmallInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RandomSmallInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomSmallInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
