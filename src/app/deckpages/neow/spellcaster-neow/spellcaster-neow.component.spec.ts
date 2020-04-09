import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpellcasterNeowComponent } from './spellcaster-neow.component';

describe('SpellcasterNeowComponent', () => {
  let component: SpellcasterNeowComponent;
  let fixture: ComponentFixture<SpellcasterNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpellcasterNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpellcasterNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
