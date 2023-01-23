import { TestBed } from '@angular/core/testing';

import { SpamDetectorService } from './spam-detector.service';

describe('SpamDetectorService', () => {
  let service: SpamDetectorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpamDetectorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
