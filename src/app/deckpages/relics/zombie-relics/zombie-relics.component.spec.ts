import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ZombieRelicsComponent } from './zombie-relics.component';

describe('ZombieRelicsComponent', () => {
  let component: ZombieRelicsComponent;
  let fixture: ComponentFixture<ZombieRelicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ZombieRelicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ZombieRelicsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
