import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedTwoNeowComponent } from './ascended-two-neow.component';

describe('AscendedTwoNeowComponent', () => {
  let component: AscendedTwoNeowComponent;
  let fixture: ComponentFixture<AscendedTwoNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedTwoNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedTwoNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
