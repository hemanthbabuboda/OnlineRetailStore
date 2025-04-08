
import { Component, OnInit } from '@angular/core';
import { Orders, LineItem } from '../../models/orders.model';
import { OrdersService } from '../../services/orders.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent {
  newOrder: Orders = { lineItems: [{ productId: 0, productName: '', quantity: 1, price: 0 }] };
  orderCreated = false;

  constructor(private ordersService: OrdersService) {}

  onSubmit() {
    console.log("in ts file before service "+ JSON.stringify(this.newOrder));
    this.ordersService.createOrder(this.newOrder).subscribe(
      (order) => {
        console.log('Order created successfully:', order);
        this.orderCreated = true;
        this.resetForm();
      },
      (error) => {
        console.error('Error creating order:', error);
      }
    );
  }

  addLineItem() {
    this.newOrder.lineItems.push({ productId: 0, productName: '', quantity: 1, price: 0 });
  }

  resetForm() {
    this.newOrder = { lineItems: [{ productId: 0, productName: '', quantity: 1, price: 0 }] };
    this.orderCreated = false;
  }
}