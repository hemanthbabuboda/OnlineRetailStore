import { Component } from '@angular/core';
import { CompositeService } from '../../../services/composite.service';
import { CompositeProduct } from '../../../models/CompositeProduct.model';
@Component({
  selector: 'app-add-product-composite',
  templateUrl: './add-product-composite.component.html',
  styleUrl: './add-product-composite.component.css'
})
export class AddProductCompositeComponent {
  products: CompositeProduct[] = [];
  newProduct: CompositeProduct = {
    productName: '',
    productDescription: '',
    productPrice: 0,
    quantity:0
  };
  selectedProduct?: CompositeProduct;

  constructor(private compositeService: CompositeService) {}

  addProduct(): void {
    this.compositeService.addProduct(this.newProduct).subscribe({
      next: (product) => {
        console.log('Product added successfully:', product);
        this.products.push(product);
        this.resetForm();
      },
      error: (error) => {
        console.error('Error adding product:', error);
      }
    });
  }


  resetForm(): void {
    this.newProduct = {
      productName: '',
      productDescription: '',
      productPrice: 0,
      quantity:0
    };
    this.selectedProduct = undefined;
  }
}