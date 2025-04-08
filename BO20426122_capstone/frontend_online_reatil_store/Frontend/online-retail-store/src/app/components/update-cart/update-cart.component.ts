import { Component } from '@angular/core';
import { Cart } from '../../models/cart.model';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-update-cart',
  templateUrl: './update-cart.component.html',
  styleUrl: './update-cart.component.css'
})
export class UpdateCartComponent {
  cartId: number = 0;
  cart: Cart | null = null;
  

  constructor(private cartService: CartService) {}

  loadCart() {
    this.cartService.getCart(this.cartId).subscribe({
      next: (cart) => {
        this.cart = cart;
        console.log('Cart loaded:', JSON.stringify(cart));
      },
      error: (error) => {
        console.error('Error loading cart', error);
        this.cart = null;
      }
    });
  }

  updateCart() {
    if (this.cart) {
      console.log(JSON.stringify(this.cart)+ " in updateCart before sending to service");
      this.cartService.updateCart(this.cart.cartId!, this.cart).subscribe({
        next: (updatedCart) => {
          console.log('Cart updated successfully:', updatedCart);
          this.cart = updatedCart;
        },
        error: (error) => {
          console.error('Error updating cart:', error);
        }
      });
    }
  }
}