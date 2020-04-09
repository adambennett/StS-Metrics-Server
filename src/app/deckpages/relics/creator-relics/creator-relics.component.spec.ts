import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatorRelicsComponent } from './creator-relics.component';

describe('CreatorRelicsComponent', () => {
  let component: CreatorRelicsComponent;
  let fixture: ComponentFixture<CreatorRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatorRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatorRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
