import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MegatypeNeowComponent } from './megatype-neow.component';

describe('MegatypeNeowComponent', () => {
  let component: MegatypeNeowComponent;
  let fixture: ComponentFixture<MegatypeNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MegatypeNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MegatypeNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
