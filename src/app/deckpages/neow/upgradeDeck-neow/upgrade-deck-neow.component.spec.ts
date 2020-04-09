import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpgradeDeckNeowComponent } from './upgrade-deck-neow.component';

describe('UpgradeDeckNeowComponent', () => {
  let component: UpgradeDeckNeowComponent;
  let fixture: ComponentFixture<UpgradeDeckNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpgradeDeckNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpgradeDeckNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
