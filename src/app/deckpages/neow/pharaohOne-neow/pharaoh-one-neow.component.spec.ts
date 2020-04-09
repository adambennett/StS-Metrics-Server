import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohOneNeowComponent } from './pharaoh-one-neow.component';

describe('PharaohOneNeowComponent', () => {
  let component: PharaohOneNeowComponent;
  let fixture: ComponentFixture<PharaohOneNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohOneNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohOneNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
