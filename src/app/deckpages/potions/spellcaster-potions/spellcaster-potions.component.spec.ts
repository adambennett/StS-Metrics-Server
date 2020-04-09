import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpellcasterPotionsComponent } from './spellcaster-potions.component';

describe('SpellcasterPotionsComponent', () => {
  let component: SpellcasterPotionsComponent;
  let fixture: ComponentFixture<SpellcasterPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpellcasterPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpellcasterPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
