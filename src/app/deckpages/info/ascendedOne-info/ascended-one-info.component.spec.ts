import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedOneInfoComponent } from './ascended-one-info.component';

describe('AscendedOneInfoComponent', () => {
  let component: AscendedOneInfoComponent;
  let fixture: ComponentFixture<AscendedOneInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedOneInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedOneInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
