import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohFourInfoComponent } from './pharaoh-four-info.component';

describe('PharaohFourInfoComponent', () => {
  let component: PharaohFourInfoComponent;
  let fixture: ComponentFixture<PharaohFourInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohFourInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohFourInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
