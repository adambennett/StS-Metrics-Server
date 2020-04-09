import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MegatypeRelicsComponent } from './megatype-relics.component';

describe('MegatypeRelicsComponent', () => {
  let component: MegatypeRelicsComponent;
  let fixture: ComponentFixture<MegatypeRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MegatypeRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MegatypeRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
