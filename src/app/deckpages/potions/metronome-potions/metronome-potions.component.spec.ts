import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MetronomePotionsComponent } from './metronome-potions.component';

describe('MetronomePotionsComponent', () => {
  let component: MetronomePotionsComponent;
  let fixture: ComponentFixture<MetronomePotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MetronomePotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MetronomePotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
