import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohFourRelicsComponent } from './pharaoh-four-relics.component';

describe('PharaohFourRelicsComponent', () => {
  let component: PharaohFourRelicsComponent;
  let fixture: ComponentFixture<PharaohFourRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohFourRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohFourRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
