import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohTwoRelicsComponent } from './pharaoh-two-relics.component';

describe('PharaohTwoRelicsComponent', () => {
  let component: PharaohTwoRelicsComponent;
  let fixture: ComponentFixture<PharaohTwoRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohTwoRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohTwoRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
