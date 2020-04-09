import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohThreeInfoComponent } from './pharaoh-three-info.component';

describe('PharaohThreeInfoComponent', () => {
  let component: PharaohThreeInfoComponent;
  let fixture: ComponentFixture<PharaohThreeInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohThreeInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohThreeInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
