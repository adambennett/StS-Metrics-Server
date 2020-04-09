import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ToonRelicsComponent } from './toon-relics.component';

describe('ToonRelicsComponent', () => {
  let component: ToonRelicsComponent;
  let fixture: ComponentFixture<ToonRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ToonRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ToonRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
