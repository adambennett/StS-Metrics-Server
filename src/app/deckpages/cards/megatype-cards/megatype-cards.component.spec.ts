import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MegatypeCardsComponent } from './megatype-cards.component';

describe('MegatypeCardsComponent', () => {
  let component: MegatypeCardsComponent;
  let fixture: ComponentFixture<MegatypeCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MegatypeCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MegatypeCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
