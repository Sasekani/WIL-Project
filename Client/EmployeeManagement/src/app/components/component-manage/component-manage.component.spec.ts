import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentManageComponent } from './component-manage.component';

describe('ComponentManageComponent', () => {
  let component: ComponentManageComponent;
  let fixture: ComponentFixture<ComponentManageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComponentManageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ComponentManageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
