import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MegatypeInfoComponent } from './megatype-info.component';

describe('MegatypeInfoComponent', () => {
  let component: MegatypeInfoComponent;
  let fixture: ComponentFixture<MegatypeInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MegatypeInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MegatypeInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
