import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MetronomeInfoComponent } from './metronome-info.component';

describe('MetronomeInfoComponent', () => {
  let component: MetronomeInfoComponent;
  let fixture: ComponentFixture<MetronomeInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MetronomeInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MetronomeInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
