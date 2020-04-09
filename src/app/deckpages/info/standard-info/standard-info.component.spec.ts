import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StandardInfoComponent } from './standard-info.component';

describe('StandardInfoComponent', () => {
  let component: StandardInfoComponent;
  let fixture: ComponentFixture<StandardInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StandardInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StandardInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
