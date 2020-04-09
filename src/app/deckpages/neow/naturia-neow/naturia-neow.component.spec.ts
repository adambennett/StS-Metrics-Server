import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NaturiaNeowComponent } from './naturia-neow.component';

describe('NaturiaNeowComponent', () => {
  let component: NaturiaNeowComponent;
  let fixture: ComponentFixture<NaturiaNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NaturiaNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NaturiaNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
