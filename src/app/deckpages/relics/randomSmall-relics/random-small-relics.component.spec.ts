import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomSmallRelicsComponent } from './random-small-relics.component';

describe('RandomSmallRelicsComponent', () => {
  let component: RandomSmallRelicsComponent;
  let fixture: ComponentFixture<RandomSmallRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RandomSmallRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomSmallRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
