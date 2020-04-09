import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatorPotionsComponent } from './creator-potions.component';

describe('CreatorPotionsComponent', () => {
  let component: CreatorPotionsComponent;
  let fixture: ComponentFixture<CreatorPotionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatorPotionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatorPotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
