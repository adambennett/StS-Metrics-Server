import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpgradeDeckPotionsComponent } from './upgrade-deck-potions.component';

describe('UpgradeDeckPotionsComponent', () => {
  let component: UpgradeDeckPotionsComponent;
  let fixture: ComponentFixture<UpgradeDeckPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpgradeDeckPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpgradeDeckPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
