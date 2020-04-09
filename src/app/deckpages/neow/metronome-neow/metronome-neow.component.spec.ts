import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MetronomeNeowComponent } from './metronome-neow.component';

describe('MetronomeNeowComponent', () => {
  let component: MetronomeNeowComponent;
  let fixture: ComponentFixture<MetronomeNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MetronomeNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MetronomeNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
