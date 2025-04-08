import { Component } from '@angular/core';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-delete-cart',
  templateUrl: './delete-cart.component.html',
  styleUrl: './delete-cart.component.css'
})
export class DeleteCartComponent {
  cartId: number | null=null;

  constructor(private cartService: CartService) {}

  deleteCart() {
    if (this.cartId) {
      console.log(this.cartId);
      this.cartService.deleteCart(this.cartId).subscribe({
        next: () => {
          console.log('Cart deleted successfully');
        },
        error: (error) => {
          console.error('Error deleting cart', error);
        }
      });
    }
  }
}