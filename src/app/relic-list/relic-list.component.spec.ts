import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RelicListComponent } from './relic-list.component';

describe('RelicListComponent', () => {
  let component: RelicListComponent;
  let fixture: ComponentFixture<RelicListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RelicListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RelicListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
