import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohOneRelicsComponent } from './pharaoh-one-relics.component';

describe('PharaohOneRelicsComponent', () => {
  let component: PharaohOneRelicsComponent;
  let fixture: ComponentFixture<PharaohOneRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohOneRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohOneRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
