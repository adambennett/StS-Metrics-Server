import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedThreeRelicsComponent } from './ascended-three-relics.component';

describe('AscendedThreeRelicsComponent', () => {
  let component: AscendedThreeRelicsComponent;
  let fixture: ComponentFixture<AscendedThreeRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedThreeRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedThreeRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
