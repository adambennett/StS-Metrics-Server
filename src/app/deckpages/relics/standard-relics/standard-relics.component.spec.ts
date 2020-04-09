import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StandardRelicsComponent } from './standard-relics.component';

describe('StandardRelicsComponent', () => {
  let component: StandardRelicsComponent;
  let fixture: ComponentFixture<StandardRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StandardRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StandardRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
