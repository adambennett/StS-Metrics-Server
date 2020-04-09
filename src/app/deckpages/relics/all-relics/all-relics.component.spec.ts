import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllRelicsComponent } from './all-relics.component';

describe('AllRelicsComponent', () => {
  let component: AllRelicsComponent;
  let fixture: ComponentFixture<AllRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
