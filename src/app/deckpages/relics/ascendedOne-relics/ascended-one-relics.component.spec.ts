import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AscendedOneRelicsComponent } from './ascended-one-relics.component';

describe('AscendedOneRelicsComponent', () => {
  let component: AscendedOneRelicsComponent;
  let fixture: ComponentFixture<AscendedOneRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AscendedOneRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AscendedOneRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
