import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Orders } from '../../../models/orders.model'; 
import { CompositeService } from '../../../services/composite.service';
@Component({
  selector: 'app-create-order-composite',
  templateUrl: './create-order-composite.component.html',
  styleUrl: './create-order-composite.component.css'
})
export class CreateOrderCompositeComponent {
  customerId: number=1;

  constructor(private compositeService: CompositeService) {}

  onSubmit(form: NgForm) {
    if (form.valid) {
      this.compositeService.createOrder(this.customerId).subscribe({
        next: (response) => {
          console.log('Order placed successfully:', response);
          // Optionally reset the form or show a success message
          form.reset();
          alert("order placed successfully!");
        },
        error: (error) => {
          console.error('Error placing order:', error);
          // Optionally show an error message to the user
        }
      });
    }
  }
}