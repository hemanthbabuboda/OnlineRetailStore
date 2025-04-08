import { Component } from '@angular/core';
import { OrdersService } from '../../services/orders.service';

@Component({
  selector: 'app-delete-order',
  templateUrl: './delete-order.component.html',
  styleUrl: './delete-order.component.css'
})
export class DeleteOrderComponent {

  orderId: number | undefined;
  successMessage: string | undefined;
  errorMessage: string | undefined;

  constructor(private ordersService: OrdersService) {}

  // Method to delete an order by its ID
  deleteOrder(): void {
    if (this.orderId !== undefined) {
      this.ordersService.deleteOrder(this.orderId).subscribe(
        () => {
          this.successMessage = `Order with ID ${this.orderId} deleted successfully!`;
          this.errorMessage = undefined;
          this.orderId = undefined;  // Clear the input field after successful deletion
        },
        (error) => {
          this.successMessage = undefined;
          this.errorMessage = `Failed to delete order. Please ensure the Order ID is correct.`;
        }
      );
    } else {
      this.errorMessage = 'Please enter a valid Order ID to delete.';
      this.successMessage = undefined;
    }
  }
}