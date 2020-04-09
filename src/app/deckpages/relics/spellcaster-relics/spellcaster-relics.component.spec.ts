import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpellcasterRelicsComponent } from './spellcaster-relics.component';

describe('SpellcasterRelicsComponent', () => {
  let component: SpellcasterRelicsComponent;
  let fixture: ComponentFixture<SpellcasterRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpellcasterRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpellcasterRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
