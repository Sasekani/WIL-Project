import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GrievanceManangementComponent } from './grievance-manangement.component';

describe('GrievanceManangementComponent', () => {
  let component: GrievanceManangementComponent;
  let fixture: ComponentFixture<GrievanceManangementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GrievanceManangementComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GrievanceManangementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
