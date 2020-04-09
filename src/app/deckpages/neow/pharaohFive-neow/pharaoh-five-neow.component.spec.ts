import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohFiveNeowComponent } from './pharaoh-five-neow.component';

describe('PharaohFiveNeowComponent', () => {
  let component: PharaohFiveNeowComponent;
  let fixture: ComponentFixture<PharaohFiveNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohFiveNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohFiveNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
