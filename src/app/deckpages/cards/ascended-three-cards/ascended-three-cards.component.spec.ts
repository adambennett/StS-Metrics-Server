import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedThreeCardsComponent } from './ascended-three-cards.component';

describe('AscendedThreeCardsComponent', () => {
  let component: AscendedThreeCardsComponent;
  let fixture: ComponentFixture<AscendedThreeCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedThreeCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedThreeCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
