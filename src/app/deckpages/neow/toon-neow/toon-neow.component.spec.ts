import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ToonNeowComponent } from './toon-neow.component';

describe('ToonNeowComponent', () => {
  let component: ToonNeowComponent;
  let fixture: ComponentFixture<ToonNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ToonNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ToonNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
