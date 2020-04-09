import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AquaRelicsComponent } from './aqua-relics.component';

describe('AquaRelicsComponent', () => {
  let component: AquaRelicsComponent;
  let fixture: ComponentFixture<AquaRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AquaRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AquaRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
