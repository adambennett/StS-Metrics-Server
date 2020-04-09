import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohOneInfoComponent } from './pharaoh-one-info.component';

describe('PharaohOneInfoComponent', () => {
  let component: PharaohOneInfoComponent;
  let fixture: ComponentFixture<PharaohOneInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohOneInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohOneInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
