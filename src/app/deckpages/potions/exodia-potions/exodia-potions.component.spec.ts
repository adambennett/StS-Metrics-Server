import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExodiaPotionsComponent } from './exodia-potions.component';

describe('ExodiaPotionsComponent', () => {
  let component: ExodiaPotionsComponent;
  let fixture: ComponentFixture<ExodiaPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExodiaPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExodiaPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
