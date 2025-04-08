import { Component } from '@angular/core';
import { Orders } from '../../models/orders.model';
import { OrdersService } from '../../services/orders.service';

@Component({
  selector: 'app-update-order',
  templateUrl: './update-order.component.html',
  styleUrl: './update-order.component.css'
})
export class UpdateOrderComponent {

  orderId: number | undefined;
  order: Orders | undefined;
  errorMessage: string | undefined;

  constructor(private ordersService: OrdersService) {}

  // Search for an order by its ID
  searchOrder(): void {
    if (this.orderId) {
      this.ordersService.getOrder(this.orderId).subscribe(
        (response: Orders) => {
          this.order = response;
          this.errorMessage = undefined;
        },
        (error) => {
          this.order = undefined;
          this.errorMessage = 'Order not found. Please check the Order ID.';
        }
      );
    }
  }

  // Update the order with modified line item quantities
  updateOrder(): void {
    if (this.order && this.order.orderId !== undefined) {
      this.ordersService.updateOrder(this.order.orderId, this.order).subscribe(
        (response) => {
          alert('Order updated successfully!');
          this.errorMessage = undefined;
        },
        (error) => {
          this.errorMessage = 'Failed to update the order. Please try again.';
        }
      );
    } else {
      this.errorMessage = 'Order ID is not available for update.';
    }
  }
}