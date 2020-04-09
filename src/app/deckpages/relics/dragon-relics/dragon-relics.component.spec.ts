import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DragonRelicsComponent } from './dragon-relics.component';

describe('DragonRelicsComponent', () => {
  let component: DragonRelicsComponent;
  let fixture: ComponentFixture<DragonRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DragonRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DragonRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
