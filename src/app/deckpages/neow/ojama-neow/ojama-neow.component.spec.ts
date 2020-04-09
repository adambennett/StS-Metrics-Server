import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OjamaNeowComponent } from './ojama-neow.component';

describe('OjamaNeowComponent', () => {
  let component: OjamaNeowComponent;
  let fixture: ComponentFixture<OjamaNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OjamaNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OjamaNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
