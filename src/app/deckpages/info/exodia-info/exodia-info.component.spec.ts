import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExodiaInfoComponent } from './exodia-info.component';

describe('ExodiaInfoComponent', () => {
  let component: ExodiaInfoComponent;
  let fixture: ComponentFixture<ExodiaInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExodiaInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExodiaInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
