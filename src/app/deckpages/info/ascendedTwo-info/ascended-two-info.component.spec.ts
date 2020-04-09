import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedTwoInfoComponent } from './ascended-two-info.component';

describe('AscendedTwoInfoComponent', () => {
  let component: AscendedTwoInfoComponent;
  let fixture: ComponentFixture<AscendedTwoInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedTwoInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedTwoInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
