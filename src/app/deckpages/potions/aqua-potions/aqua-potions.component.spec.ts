import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AquaPotionsComponent } from './aqua-potions.component';

describe('AquaPotionsComponent', () => {
  let component: AquaPotionsComponent;
  let fixture: ComponentFixture<AquaPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AquaPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AquaPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
