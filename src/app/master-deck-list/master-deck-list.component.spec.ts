import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MasterDeckListComponent } from './master-deck-list.component';

describe('MasterDeckListComponent', () => {
  let component: MasterDeckListComponent;
  let fixture: ComponentFixture<MasterDeckListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MasterDeckListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MasterDeckListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
