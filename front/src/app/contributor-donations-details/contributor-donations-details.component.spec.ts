import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ContributorDonationsDetailsComponent } from './contributor-donations-details.component';

describe('ContributorDonationsDetailsComponent', () => {
  let component: ContributorDonationsDetailsComponent;
  let fixture: ComponentFixture<ContributorDonationsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ContributorDonationsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ContributorDonationsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
