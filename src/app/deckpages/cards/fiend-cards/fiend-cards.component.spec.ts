import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FiendCardsComponent } from './fiend-cards.component';

describe('FiendCardsComponent', () => {
  let component: FiendCardsComponent;
  let fixture: ComponentFixture<FiendCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FiendCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FiendCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
