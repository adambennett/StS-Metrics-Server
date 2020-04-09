import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohTwoNeowComponent } from './pharaoh-two-neow.component';

describe('PharaohTwoNeowComponent', () => {
  let component: PharaohTwoNeowComponent;
  let fixture: ComponentFixture<PharaohTwoNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohTwoNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohTwoNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
