import { Injectable } from '@angular/core';
import { Customer } from '../models/Customer.model';
import { CompositeProduct } from '../models/CompositeProduct.model';
import { Order } from '../models/order.model';
import { Cart } from '../models/cart.model';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CompleteCustomerOrderDetails } from '../models/CompleteCustomerOrderDetails.model';

@Injectable({
  providedIn: 'root'
})
export class CompositeService {

  private baseUrl = 'http://localhost:3333/api/shoppingservice';

  constructor(private http: HttpClient) {}

  // Add customer
  addCustomer(customer: Customer): Observable<Customer> {
    console.log('Sending request to backend:', customer);  // Debugging line
    return this.http.post<Customer>(`${this.baseUrl}/customer`, customer)
      .pipe(
        catchError(this.handleError)  // Error handling
      );
  }

  addProduct(product: CompositeProduct): Observable<CompositeProduct> {
    console.log("sending to backend: ",JSON.stringify(product));
    return this.http.post<CompositeProduct>(`${this.baseUrl}/products`, product);
  }

  addProductToCart(customerId:number ,cart: Cart): Observable<Cart> {
    console.log('Cart before sending in service:', JSON.stringify(cart));
    return this.http.put<Cart>(`${this.baseUrl}/customer/${customerId}/cart`, cart);
  }

  createOrder(customerId: number): Observable<Order> {
    return this.http.post<Order>(`${this.baseUrl}/customer/${customerId}/orders`,customerId);
  }

  // getOrders()
  // getOrders(customerId: number): Observable<CompleteCustomerOrderDetails> {
  //   return this.http.get<CompleteCustomerOrderDetails>(`/api/customer/${customerId}/orders`);
  // }

  // getOrder(customerId: number): Observable<Orders> {
  //   return this.http.get<Orders>(`${this.baseUrl}/customer/${customerId}/orders`);
  // }
  getAllOrders(customerId: number): Observable<CompleteCustomerOrderDetails> {
    return this.http.get<CompleteCustomerOrderDetails>(`${this.baseUrl}/customer/${customerId}/orders`);
  }

  // Error handling method
  private handleError(error: HttpErrorResponse) {
    console.error('Backend returned an error:', error); // Log error to the console

    // Customize error message if necessary
    let errorMessage = 'Something went wrong with the request.';
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Client-side error: ${error.error.message}`;
    } else {
      // Server-side error
      errorMessage = `Server-side error: ${error.status} ${error.message}`;
    }
    return throwError(() => new Error(errorMessage));
  }
}
