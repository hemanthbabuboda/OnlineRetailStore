import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { Customer } from '../../models/Customer.model';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent {

  updatedCustomer : Customer = {
    customerId: undefined,
    customerName: '',
    customerEmail: '',
    customerBillingAddress: [{
      doorNo: '',
      streetName: '',
      layout: '',
      city: '',
      pincode: ''
    }],
    customerShippingAddress: [{
      doorNo: '',
      streetName: '',
      layout: '',
      city: '',
      pincode: ''
    }]
  };

  constructor(private http: HttpClient,private customerService: CustomerService) {}

  updateCustomer() {
    console.log("In ts file before service = "+this.updatedCustomer.customerId+"  "+JSON.stringify(this.updatedCustomer))
    this.customerService.updateCustomer(this.updatedCustomer.customerId, this.updatedCustomer)
  .subscribe(response => {
    console.log('Customer updated:', response);
  }, error => {
    console.error('Error updating customer:', error);
  });
  }
  
}