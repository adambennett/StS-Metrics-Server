import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohFourNeowComponent } from './pharaoh-four-neow.component';

describe('PharaohFourNeowComponent', () => {
  let component: PharaohFourNeowComponent;
  let fixture: ComponentFixture<PharaohFourNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohFourNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohFourNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
