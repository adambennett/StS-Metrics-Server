import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MetronomeCardsComponent } from './metronome-cards.component';

describe('MetronomeCardsComponent', () => {
  let component: MetronomeCardsComponent;
  let fixture: ComponentFixture<MetronomeCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MetronomeCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MetronomeCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
