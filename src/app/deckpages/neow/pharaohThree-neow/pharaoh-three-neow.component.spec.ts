import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohThreeNeowComponent } from './pharaoh-three-neow.component';

describe('PharaohThreeNeowComponent', () => {
  let component: PharaohThreeNeowComponent;
  let fixture: ComponentFixture<PharaohThreeNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohThreeNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohThreeNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
