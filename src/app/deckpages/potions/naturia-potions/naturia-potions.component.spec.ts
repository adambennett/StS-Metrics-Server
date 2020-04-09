import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NaturiaPotionsComponent } from './naturia-potions.component';

describe('NaturiaPotionsComponent', () => {
  let component: NaturiaPotionsComponent;
  let fixture: ComponentFixture<NaturiaPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NaturiaPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NaturiaPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
