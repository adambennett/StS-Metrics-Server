import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedOneCardsComponent } from './ascended-one-cards.component';

describe('AscendedOneCardsComponent', () => {
  let component: AscendedOneCardsComponent;
  let fixture: ComponentFixture<AscendedOneCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedOneCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedOneCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
