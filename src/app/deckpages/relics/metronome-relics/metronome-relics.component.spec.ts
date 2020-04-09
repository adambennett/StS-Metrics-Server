import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MetronomeRelicsComponent } from './metronome-relics.component';

describe('MetronomeRelicsComponent', () => {
  let component: MetronomeRelicsComponent;
  let fixture: ComponentFixture<MetronomeRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MetronomeRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MetronomeRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
