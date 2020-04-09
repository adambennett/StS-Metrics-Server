import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpgradeDeckCardsComponent } from './upgrade-deck-cards.component';

describe('UpgradeDeckCardsComponent', () => {
  let component: UpgradeDeckCardsComponent;
  let fixture: ComponentFixture<UpgradeDeckCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpgradeDeckCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpgradeDeckCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
