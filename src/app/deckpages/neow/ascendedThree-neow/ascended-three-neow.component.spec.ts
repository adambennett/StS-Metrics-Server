import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedThreeNeowComponent } from './ascended-three-neow.component';

describe('AscendedThreeNeowComponent', () => {
  let component: AscendedThreeNeowComponent;
  let fixture: ComponentFixture<AscendedThreeNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedThreeNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedThreeNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
