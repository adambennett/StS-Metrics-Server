import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DragonCardsComponent } from './dragon-cards.component';

describe('DragonCardsComponent', () => {
  let component: DragonCardsComponent;
  let fixture: ComponentFixture<DragonCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DragonCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DragonCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
