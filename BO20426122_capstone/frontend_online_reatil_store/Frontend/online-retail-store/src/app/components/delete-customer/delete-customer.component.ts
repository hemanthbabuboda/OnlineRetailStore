import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-delete-customer',
  templateUrl: './delete-customer.component.html',
  styleUrls: ['./delete-customer.component.css']
})
export class DeleteCustomerComponent {
  customerId: number | null = null;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private customerService: CustomerService) {}

  deleteCustomer() {
    if (this.customerId !== null) {
      this.customerService.deleteCustomer(this.customerId).subscribe({
        next: () => {
          this.successMessage = 'Customer deleted successfully.';
          this.errorMessage = null;
        },
        error: (error) => {
          console.error('Error deleting customer:', error);
          this.errorMessage = 'Error deleting customer.';
          this.successMessage = null;
        }
      });
    }
  }
}
