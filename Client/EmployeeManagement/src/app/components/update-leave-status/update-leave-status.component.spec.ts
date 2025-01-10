import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateLeaveStatusComponent } from './update-leave-status.component';

describe('UpdateLeaveStatusComponent', () => {
  let component: UpdateLeaveStatusComponent;
  let fixture: ComponentFixture<UpdateLeaveStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateLeaveStatusComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateLeaveStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
