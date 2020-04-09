import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncrementRelicsComponent } from './increment-relics.component';

describe('IncrementRelicsComponent', () => {
  let component: IncrementRelicsComponent;
  let fixture: ComponentFixture<IncrementRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncrementRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncrementRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
