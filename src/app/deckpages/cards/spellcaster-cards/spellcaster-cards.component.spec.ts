import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpellcasterCardsComponent } from './spellcaster-cards.component';

describe('SpellcasterCardsComponent', () => {
  let component: SpellcasterCardsComponent;
  let fixture: ComponentFixture<SpellcasterCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpellcasterCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpellcasterCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
