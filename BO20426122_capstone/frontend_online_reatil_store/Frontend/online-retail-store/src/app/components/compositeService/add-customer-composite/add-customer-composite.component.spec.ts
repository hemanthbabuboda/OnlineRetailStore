import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCustomerCompositeComponent } from './add-customer-composite.component';

describe('AddCustomerCompositeComponent', () => {
  let component: AddCustomerCompositeComponent;
  let fixture: ComponentFixture<AddCustomerCompositeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddCustomerCompositeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCustomerCompositeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
