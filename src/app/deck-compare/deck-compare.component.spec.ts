import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeckCompareComponent } from './deck-compare.component';

describe('DeckCompareComponent', () => {
  let component: DeckCompareComponent;
  let fixture: ComponentFixture<DeckCompareComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeckCompareComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeckCompareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
