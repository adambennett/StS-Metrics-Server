import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OjamaCardsComponent } from './ojama-cards.component';

describe('OjamaCardsComponent', () => {
  let component: OjamaCardsComponent;
  let fixture: ComponentFixture<OjamaCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OjamaCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OjamaCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
