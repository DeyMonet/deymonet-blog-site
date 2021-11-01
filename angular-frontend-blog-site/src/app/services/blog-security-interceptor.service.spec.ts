import { TestBed } from '@angular/core/testing';

import { BlogSecurityInterceptorService } from './blog-security-interceptor.service';

describe('BlogSecurityInterceptorService', () => {
  let service: BlogSecurityInterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BlogSecurityInterceptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
