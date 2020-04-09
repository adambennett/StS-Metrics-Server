import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsectCardsComponent } from './insect-cards.component';

describe('InsectCardsComponent', () => {
  let component: InsectCardsComponent;
  let fixture: ComponentFixture<InsectCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsectCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsectCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
