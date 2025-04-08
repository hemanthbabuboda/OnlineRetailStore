import { Component } from '@angular/core';
import { Orders } from '../../models/orders.model';
import { OrdersService } from '../../services/orders.service';

@Component({
  selector: 'app-search-order',
  templateUrl: './search-order.component.html',
  styleUrl: './search-order.component.css'
})
export class SearchOrderComponent {
  orderId: number | undefined;
  order: Orders | undefined;
  errorMessage: string | undefined;

  constructor(private ordersService: OrdersService) {}

  // Method to search for an order by ID
  searchOrder(): void {
    if (this.orderId) {
      this.ordersService.getOrder(this.orderId).subscribe(
        (response: Orders) => {
          this.order = response;
          this.errorMessage = undefined; // Clear the error message if found
        },
        (error) => {
          this.order = undefined; // Clear the order details if not found
          this.errorMessage = 'Order not found. Please check the Order ID.';
        }
      );
    }
  }
}