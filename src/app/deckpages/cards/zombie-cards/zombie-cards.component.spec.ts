import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZombieCardsComponent } from './zombie-cards.component';

describe('ZombieCardsComponent', () => {
  let component: ZombieCardsComponent;
  let fixture: ComponentFixture<ZombieCardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZombieCardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZombieCardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
