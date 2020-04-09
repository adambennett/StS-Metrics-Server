import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NaturiaRelicsComponent } from './naturia-relics.component';

describe('NaturiaRelicsComponent', () => {
  let component: NaturiaRelicsComponent;
  let fixture: ComponentFixture<NaturiaRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NaturiaRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NaturiaRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
