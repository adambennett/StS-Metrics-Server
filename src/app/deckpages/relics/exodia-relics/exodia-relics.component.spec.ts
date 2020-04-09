import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExodiaRelicsComponent } from './exodia-relics.component';

describe('ExodiaRelicsComponent', () => {
  let component: ExodiaRelicsComponent;
  let fixture: ComponentFixture<ExodiaRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExodiaRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExodiaRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
