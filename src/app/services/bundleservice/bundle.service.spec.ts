import { TestBed } from '@angular/core/testing';
import {BundleService} from "./bundle.service";

describe('BlogService', () => {
  let service: BundleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BundleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
