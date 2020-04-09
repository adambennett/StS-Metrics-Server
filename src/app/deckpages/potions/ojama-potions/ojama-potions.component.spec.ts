import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OjamaPotionsComponent } from './ojama-potions.component';

describe('OjamaPotionsComponent', () => {
  let component: OjamaPotionsComponent;
  let fixture: ComponentFixture<OjamaPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OjamaPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OjamaPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
