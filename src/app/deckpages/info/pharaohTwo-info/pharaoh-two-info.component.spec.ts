import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohTwoInfoComponent } from './pharaoh-two-info.component';

describe('PharaohTwoInfoComponent', () => {
  let component: PharaohTwoInfoComponent;
  let fixture: ComponentFixture<PharaohTwoInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohTwoInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohTwoInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
