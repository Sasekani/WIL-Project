import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaysliplistComponent } from './paysliplist.component';

describe('PaysliplistComponent', () => {
  let component: PaysliplistComponent;
  let fixture: ComponentFixture<PaysliplistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PaysliplistComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PaysliplistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
