import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AquaInfoComponent } from './aqua-info.component';

describe('AquaInfoComponent', () => {
  let component: AquaInfoComponent;
  let fixture: ComponentFixture<AquaInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AquaInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AquaInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
