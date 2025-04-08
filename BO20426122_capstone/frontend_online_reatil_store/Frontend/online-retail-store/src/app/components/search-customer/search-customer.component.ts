import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { Customer } from '../../models/Customer.model';

@Component({
  selector: 'app-search-customer',
  templateUrl: './search-customer.component.html',
  styleUrls: ['./search-customer.component.css']
})
export class SearchCustomerComponent {
  customerId: number | null = null;
  customer: Customer | null = null;
  errorMessage: string | null = null;

  constructor(private customerService: CustomerService) {}

  searchCustomer() {
    if (this.customerId !== null) {
      this.customerService.getCustomer(this.customerId).subscribe({
        next: (customer) => {
          this.customer = customer;
          this.errorMessage = null;
        },
        error: (error) => {
          console.error('Error searching customer:', error);
          this.errorMessage = 'Customer not found.';
          this.customer = null;
        }
      });
    }
  }
}
