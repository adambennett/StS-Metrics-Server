import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PharaohThreeRelicsComponent } from './pharaoh-three-relics.component';

describe('PharaohThreeRelicsComponent', () => {
  let component: PharaohThreeRelicsComponent;
  let fixture: ComponentFixture<PharaohThreeRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PharaohThreeRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PharaohThreeRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
