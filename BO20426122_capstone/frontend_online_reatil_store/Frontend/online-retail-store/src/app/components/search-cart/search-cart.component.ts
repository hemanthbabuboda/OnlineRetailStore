import { Component } from '@angular/core';
import { Cart } from '../../models/cart.model';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-search-cart',
  templateUrl: './search-cart.component.html',
  styleUrl: './search-cart.component.css'
})
export class SearchCartComponent {

  customerId: number = 0;
  cart: Cart | null = null;

  constructor(private cartService: CartService) {}

  searchCart() {
    if (this.customerId) {
      this.cartService.getCart(this.customerId).subscribe({
        next: (cart) => {
          this.cart = cart;
          console.log('Cart found:', cart);
        },
        error: (error) => {
          console.error('Error fetching cart', error);
          this.cart = null;
        }
      });
    }
  }
}
