import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NaturiaInfoComponent } from './naturia-info.component';

describe('NaturiaInfoComponent', () => {
  let component: NaturiaInfoComponent;
  let fixture: ComponentFixture<NaturiaInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NaturiaInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NaturiaInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
