import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StandardCardsComponent } from './standard-cards.component';

describe('StandardCardsComponent', () => {
  let component: StandardCardsComponent;
  let fixture: ComponentFixture<StandardCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StandardCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StandardCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
