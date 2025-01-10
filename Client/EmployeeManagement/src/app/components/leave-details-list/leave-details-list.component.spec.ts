import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaveDetailsListComponent } from './leave-details-list.component';

describe('LeaveDetailsListComponent', () => {
  let component: LeaveDetailsListComponent;
  let fixture: ComponentFixture<LeaveDetailsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeaveDetailsListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LeaveDetailsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
