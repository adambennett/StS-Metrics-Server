import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohFiveInfoComponent } from './pharaoh-five-info.component';

describe('PharaohFiveInfoComponent', () => {
  let component: PharaohFiveInfoComponent;
  let fixture: ComponentFixture<PharaohFiveInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohFiveInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohFiveInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
