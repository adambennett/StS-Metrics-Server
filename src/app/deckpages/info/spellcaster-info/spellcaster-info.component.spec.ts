import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpellcasterInfoComponent } from './spellcaster-info.component';

describe('SpellcasterInfoComponent', () => {
  let component: SpellcasterInfoComponent;
  let fixture: ComponentFixture<SpellcasterInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpellcasterInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpellcasterInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
