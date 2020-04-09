import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MegatypePotionsComponent } from './megatype-potions.component';

describe('MegatypePotionsComponent', () => {
  let component: MegatypePotionsComponent;
  let fixture: ComponentFixture<MegatypePotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MegatypePotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MegatypePotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
