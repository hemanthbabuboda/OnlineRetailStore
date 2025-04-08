import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Customer } from '../models/Customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseUrl = 'http://localhost:3333/api/customer';

  constructor(private http: HttpClient) {}

  // Add customer
  addCustomer(customer: Customer): Observable<Customer> {
    console.log('Sending request to backend:', customer);  // Debugging line
    return this.http.post<Customer>(`${this.baseUrl}/addCustomer`, customer)
      .pipe(
        catchError(this.handleError)  // Error handling
      );
  }

  // Get customer by ID
  getCustomer(customerId: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.baseUrl}/searchCustomer/${customerId}`)
      .pipe(
        catchError(this.handleError)  // Error handling
      );
  }

  // Update customer
  updateCustomer(customerId: number|undefined, customer: Customer): Observable<Customer> {
    console.log(customer + "  this is before put in service " + customerId);
    return this.http.put<Customer>(`${this.baseUrl}/updateCustomer/${customerId}`, customer)
      .pipe(
        catchError(this.handleError)  // Error handling
      );
  }

  // Delete customer
  deleteCustomer(customerId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/deleteCustomer/${customerId}`)
      .pipe(
        catchError(this.handleError)  // Error handling
      );
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
