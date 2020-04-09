import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpgradeDeckRelicsComponent } from './upgrade-deck-relics.component';

describe('UpgradeDeckRelicsComponent', () => {
  let component: UpgradeDeckRelicsComponent;
  let fixture: ComponentFixture<UpgradeDeckRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpgradeDeckRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpgradeDeckRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
