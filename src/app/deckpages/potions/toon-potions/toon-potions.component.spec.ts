import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ToonPotionsComponent } from './toon-potions.component';

describe('ToonPotionsComponent', () => {
  let component: ToonPotionsComponent;
  let fixture: ComponentFixture<ToonPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ToonPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ToonPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
