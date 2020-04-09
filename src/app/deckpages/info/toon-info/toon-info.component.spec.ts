import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ToonInfoComponent } from './toon-info.component';

describe('ToonInfoComponent', () => {
  let component: ToonInfoComponent;
  let fixture: ComponentFixture<ToonInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ToonInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ToonInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
