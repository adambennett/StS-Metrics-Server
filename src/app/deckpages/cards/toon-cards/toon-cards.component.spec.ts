import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ToonCardsComponent } from './toon-cards.component';

describe('ToonCardsComponent', () => {
  let component: ToonCardsComponent;
  let fixture: ComponentFixture<ToonCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ToonCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ToonCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
