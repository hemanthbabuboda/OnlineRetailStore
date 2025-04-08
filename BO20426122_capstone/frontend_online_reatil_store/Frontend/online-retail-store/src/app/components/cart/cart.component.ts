
import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { Cart, LineItem } from '../../models/cart.model';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})


export class CartComponent {
  cart: Cart = 
  {
    lineItem: [
      {
        productId: 0,
        productName: '',
        quantity: 1,
        price: 0
      }
    ]
  };

  constructor(private cartService: CartService) {}

  // Add a new line item to the cart
  addLineItem() {
    this.cart.lineItem.push({
      productId: 0,
      productName: '',
      quantity: 1,
      price: 0
    });
  }

  // Remove a line item from the cart by index
  removeLineItem(index: number) {
    if (this.cart.lineItem.length > 1) {
      this.cart.lineItem.splice(index, 1);
    }
  }

  // Submit the cart form and either create or update the cart
  submitCart(form: NgForm) {
    if (form.valid) {
     {
      console.log(this.cart);
        // Create a new cart
        this.cartService.createCart(this.cart).subscribe({
          next: (response) => {
            console.log('Cart created successfully', response);
            this.cart.cartId = response.cartId;
          },
          error: (error) => {
            console.error('Error creating cart', error);
          }
        });
      }
    }
  }
}
