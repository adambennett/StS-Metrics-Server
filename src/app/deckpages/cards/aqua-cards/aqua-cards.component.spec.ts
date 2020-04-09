import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AquaCardsComponent } from './aqua-cards.component';

describe('AquaCardsComponent', () => {
  let component: AquaCardsComponent;
  let fixture: ComponentFixture<AquaCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AquaCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AquaCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
