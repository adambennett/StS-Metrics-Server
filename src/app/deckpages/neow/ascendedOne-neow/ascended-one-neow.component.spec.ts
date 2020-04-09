import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedOneNeowComponent } from './ascended-one-neow.component';

describe('AscendedOneNeowComponent', () => {
  let component: AscendedOneNeowComponent;
  let fixture: ComponentFixture<AscendedOneNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedOneNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedOneNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
