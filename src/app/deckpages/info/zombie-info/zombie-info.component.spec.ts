import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZombieInfoComponent } from './zombie-info.component';

describe('ZombieInfoComponent', () => {
  let component: ZombieInfoComponent;
  let fixture: ComponentFixture<ZombieInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZombieInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZombieInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
