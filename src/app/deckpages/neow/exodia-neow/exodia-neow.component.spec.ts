import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExodiaNeowComponent } from './exodia-neow.component';

describe('ExodiaNeowComponent', () => {
  let component: ExodiaNeowComponent;
  let fixture: ComponentFixture<ExodiaNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExodiaNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExodiaNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
