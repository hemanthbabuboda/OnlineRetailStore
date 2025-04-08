import { Component } from '@angular/core';
import { CompositeService } from '../../../services/composite.service';
import { Customer, CustomerAddress } from '../../../models/Customer.model';

@Component({
  selector: 'app-add-customer-composite',
  templateUrl: './add-customer-composite.component.html',
  styleUrl: './add-customer-composite.component.css'
})
export class AddCustomerCompositeComponent {
  customers: Customer[] = [];
  newCustomer: Customer = {
    customerName: '',
    customerEmail: '',
    customerBillingAddress: [{ doorNo: '', streetName: '', layout: '', city: '', pincode: '' }],
    customerShippingAddress: [{ doorNo: '', streetName: '', layout: '', city: '', pincode: '' }],
  };

  constructor(private compositeService: CompositeService) {}

  addCustomer() {
    console.log('Sending customer data:', this.newCustomer);
    this.compositeService.addCustomer(this.newCustomer).subscribe({
      next: (customer) => {
        console.log('Customer added successfully. Backend response:', customer);
        this.customers.push(customer);
        this.resetForm();
      },
      error: (error) => {
        console.error('Error adding customer:', error);
      }
    });
  }

  resetForm() {
    this.newCustomer = {
      customerName: '',
      customerEmail: '',
      customerBillingAddress: [{ doorNo: '', streetName: '', layout: '', city: '', pincode: '' }],
      customerShippingAddress: [{ doorNo: '', streetName: '', layout: '', city: '', pincode: '' }],
    };
  }
}
