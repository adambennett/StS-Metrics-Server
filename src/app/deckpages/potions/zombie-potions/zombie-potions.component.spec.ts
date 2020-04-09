import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZombiePotionsComponent } from './zombie-potions.component';

describe('ZombiePotionsComponent', () => {
  let component: ZombiePotionsComponent;
  let fixture: ComponentFixture<ZombiePotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZombiePotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZombiePotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
