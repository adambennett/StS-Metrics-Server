import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohFiveRelicsComponent } from './pharaoh-five-relics.component';

describe('PharaohFiveRelicsComponent', () => {
  let component: PharaohFiveRelicsComponent;
  let fixture: ComponentFixture<PharaohFiveRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohFiveRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohFiveRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
