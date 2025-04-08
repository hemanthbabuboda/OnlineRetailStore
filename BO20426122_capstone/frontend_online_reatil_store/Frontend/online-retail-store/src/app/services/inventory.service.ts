
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Inventory } from '../models/Inventory.model';

@Injectable({
  providedIn: 'root',
})
export class InventoryService {
  private baseUrl = 'http://localhost:3333/api/inventory';

  constructor(private http: HttpClient) {}

  addInventory(inventory: Inventory): Observable<Inventory> {
    return this.http.post<Inventory>(`${this.baseUrl}`, inventory);
  }

  updateInventory(id: number, inventory: Inventory): Observable<Inventory> {
    return this.http.put<Inventory>(`${this.baseUrl}/${id}`, inventory);
  }

  getInventory(id: number): Observable<Inventory> {
    return this.http.get<Inventory>(`${this.baseUrl}/${id}`);
  }

  getInventoryByProductId(productId: number): Observable<Inventory> {
    return this.http.get<Inventory>(`${this.baseUrl}/product/${productId}`);
  }

  deleteInventory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}

