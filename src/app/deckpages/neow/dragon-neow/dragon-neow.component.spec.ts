import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DragonNeowComponent } from './dragon-neow.component';

describe('DragonNeowComponent', () => {
  let component: DragonNeowComponent;
  let fixture: ComponentFixture<DragonNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DragonNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DragonNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
