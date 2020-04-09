import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpgradeDeckInfoComponent } from './upgrade-deck-info.component';

describe('UpgradeDeckInfoComponent', () => {
  let component: UpgradeDeckInfoComponent;
  let fixture: ComponentFixture<UpgradeDeckInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpgradeDeckInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpgradeDeckInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
