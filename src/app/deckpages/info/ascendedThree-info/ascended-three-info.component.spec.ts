import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedThreeInfoComponent } from './ascended-three-info.component';

describe('AscendedThreeInfoComponent', () => {
  let component: AscendedThreeInfoComponent;
  let fixture: ComponentFixture<AscendedThreeInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedThreeInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedThreeInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
