import { Component } from '@angular/core';
import { CompositeService } from '../../../services/composite.service';
import { CompleteCustomerOrderDetails } from '../../../models/CompleteCustomerOrderDetails.model';

@Component({
  selector: 'app-get-orders-composite',
  templateUrl: './get-orders-composite.component.html',
  styleUrl: './get-orders-composite.component.css'
})
export class GetOrdersCompositeComponent {
  customerId: number = 0;
  orderDetails: CompleteCustomerOrderDetails | null = null;
  errorMessage: string | null = null;

  constructor(private compositeService: CompositeService) {}

  fetchOrders() {
    this.compositeService.getAllOrders(this.customerId).subscribe(
      (data) => {
        this.orderDetails = data;
        this.errorMessage = null;
      },
      (error) => {
        this.errorMessage = 'Error fetching orders. Please check the customer ID.';
        this.orderDetails = null;
      }
    );
  }
}