import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RandomBigRelicsComponent } from './random-big-relics.component';

describe('RandomBigRelicsComponent', () => {
  let component: RandomBigRelicsComponent;
  let fixture: ComponentFixture<RandomBigRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RandomBigRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomBigRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
