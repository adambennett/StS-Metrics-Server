import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatorInfoComponent } from './creator-info.component';

describe('CreatorInfoComponent', () => {
  let component: CreatorInfoComponent;
  let fixture: ComponentFixture<CreatorInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatorInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatorInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
