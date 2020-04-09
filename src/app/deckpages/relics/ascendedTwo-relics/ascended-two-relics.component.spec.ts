import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedTwoRelicsComponent } from './ascended-two-relics.component';

describe('AscendedTwoRelicsComponent', () => {
  let component: AscendedTwoRelicsComponent;
  let fixture: ComponentFixture<AscendedTwoRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedTwoRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedTwoRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
