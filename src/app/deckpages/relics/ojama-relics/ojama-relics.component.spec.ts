import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OjamaRelicsComponent } from './ojama-relics.component';

describe('OjamaRelicsComponent', () => {
  let component: OjamaRelicsComponent;
  let fixture: ComponentFixture<OjamaRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OjamaRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OjamaRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
