import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StandardNeowComponent } from './standard-neow.component';

describe('StandardNeowComponent', () => {
  let component: StandardNeowComponent;
  let fixture: ComponentFixture<StandardNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StandardNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StandardNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
