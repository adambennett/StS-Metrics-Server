import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AquaNeowComponent } from './aqua-neow.component';

describe('AquaNeowComponent', () => {
  let component: AquaNeowComponent;
  let fixture: ComponentFixture<AquaNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AquaNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AquaNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
