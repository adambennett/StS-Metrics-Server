import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatorNeowComponent } from './creator-neow.component';

describe('CreatorNeowComponent', () => {
  let component: CreatorNeowComponent;
  let fixture: ComponentFixture<CreatorNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatorNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatorNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
