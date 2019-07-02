import { TestBed } from '@angular/core/testing';

import { ProjectUpdateService } from './project-update.service';

describe('ProjectUpdateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProjectUpdateService = TestBed.get(ProjectUpdateService);
    expect(service).toBeTruthy();
  });
});
