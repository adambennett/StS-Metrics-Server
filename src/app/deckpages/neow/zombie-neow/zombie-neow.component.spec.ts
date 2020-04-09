import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZombieNeowComponent } from './zombie-neow.component';

describe('ZombieNeowComponent', () => {
  let component: ZombieNeowComponent;
  let fixture: ComponentFixture<ZombieNeowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZombieNeowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZombieNeowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
