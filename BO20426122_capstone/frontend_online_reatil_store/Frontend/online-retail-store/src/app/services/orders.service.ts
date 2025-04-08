import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Orders } from '../models/orders.model';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {
  private baseUrl = 'http://localhost:3333/api/order';

  constructor(private http: HttpClient) {}

  createOrder(order: Orders): Observable<Orders> {
    return this.http.post<Orders>(`${this.baseUrl}`, order);
  }

  updateOrder(orderId: number, order: Orders): Observable<Orders> {
    return this.http.put<Orders>(`${this.baseUrl}/${orderId}`, order);
  }

  deleteOrder(orderId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${orderId}`);
  }

  getOrder(orderId: number): Observable<Orders> {
    return this.http.get<Orders>(`${this.baseUrl}/${orderId}`);
  }
}
