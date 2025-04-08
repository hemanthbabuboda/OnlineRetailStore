
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cart } from '../models/cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseUrl = 'http://localhost:3333/api/cart';

  constructor(private http: HttpClient) {}

  createCart(cart: Cart): Observable<Cart> {
    console.log('Cart before sending in service:', JSON.stringify(cart));
    return this.http.post<Cart>(`${this.baseUrl}`, cart);
  }

  updateCart(cartId: number, cart: Cart): Observable<Cart> {
    return this.http.put<Cart>(`${this.baseUrl}/${cartId}`, cart);
  }

  deleteCart(cartId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${cartId}`);
  }

  getCart(customerId: number): Observable<Cart> {
    return this.http.get<Cart>(`${this.baseUrl}/${customerId}`);
  }
}
