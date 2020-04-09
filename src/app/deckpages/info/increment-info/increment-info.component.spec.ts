import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncrementInfoComponent } from './increment-info.component';

describe('IncrementInfoComponent', () => {
  let component: IncrementInfoComponent;
  let fixture: ComponentFixture<IncrementInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncrementInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncrementInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
