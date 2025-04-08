import { Component } from '@angular/core';
import { CartService } from '../../../services/cart.service';
import { Cart, LineItem } from '../../../models/cart.model';
import { NgForm } from '@angular/forms';

import { CompositeService } from '../../../services/composite.service';
import { CompositeProduct } from '../../../models/CompositeProduct.model';

@Component({
  selector: 'app-add-product-to-cart',
  templateUrl: './add-product-to-cart.component.html',
  styleUrl: './add-product-to-cart.component.css'
})

export class AddProductToCartComponent {
  cart: Cart = {
    lineItem: [
      {
        productId: 0,
        productName: '',
        quantity: 1,
        price: 0
      }
    ]
  };
  
  customerId: number=0;

  constructor(private compositeService: CompositeService) {}

 

  submitCart(form: NgForm) {
    if (form.valid) {
      console.log('Submitting cart:', this.cart);
      this.compositeService.addProductToCart(this.customerId, this.cart).subscribe({
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
