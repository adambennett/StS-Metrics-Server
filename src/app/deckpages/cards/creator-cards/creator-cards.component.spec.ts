import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatorCardsComponent } from './creator-cards.component';

describe('CreatorCardsComponent', () => {
  let component: CreatorCardsComponent;
  let fixture: ComponentFixture<CreatorCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatorCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatorCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
