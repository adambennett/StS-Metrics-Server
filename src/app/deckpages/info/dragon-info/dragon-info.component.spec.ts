import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DragonInfoComponent } from './dragon-info.component';

describe('DragonInfoComponent', () => {
  let component: DragonInfoComponent;
  let fixture: ComponentFixture<DragonInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DragonInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DragonInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
