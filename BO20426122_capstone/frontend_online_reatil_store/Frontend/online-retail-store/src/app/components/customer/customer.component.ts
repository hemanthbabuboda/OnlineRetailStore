import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { Customer, CustomerAddress } from '../../models/Customer.model';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent {
  customers: Customer[] = [];
  newCustomer: Customer = {
    customerName: '',
    customerEmail: '',
    customerBillingAddress: [{ doorNo: '', streetName: '', layout: '', city: '', pincode: '' }],
    customerShippingAddress: [{ doorNo: '', streetName: '', layout: '', city: '', pincode: '' }],
  };

  constructor(private customerService: CustomerService) {}

  addCustomer() {
    console.log('Sending customer data:', this.newCustomer);
    this.customerService.addCustomer(this.newCustomer).subscribe({
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
