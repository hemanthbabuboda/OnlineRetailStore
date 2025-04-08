import { Component } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/Product.model';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent {
  productId: number | null = null;
  product: Product | null = null;
  errorMessage: string | null = null;
  form:boolean =true;
  constructor(private productService: ProductService) {}

  getProduct(): void {
    if (this.productId !== null) {
      this.productService.getProduct(this.productId).subscribe({
        next: (product) => {
          this.product = product;
          this.errorMessage = null;
        },
        error: (error) => {
          console.error('Error fetching product:', error);
          this.errorMessage = 'Product not found.';
          this.product = null;
        }
      });
    }
  }

  updateProduct(): void {
    if (this.productId === null || this.product === null) {
      this.errorMessage = 'Product data or ID is missing.';
      return;
    }
  
    this.productService.updateProduct(this.productId, this.product).subscribe({
      next: (updatedProduct) => {
        console.log('Product updated successfully:', updatedProduct);
        this.errorMessage = null;
        // Optionally, you might want to redirect or clear form
        this.form=false;
        this.productId=null;
      },
      error: (error) => {
        console.error('Error updating product:', error);
        this.errorMessage = 'Error updating product.';
      }
    });
  }
}
