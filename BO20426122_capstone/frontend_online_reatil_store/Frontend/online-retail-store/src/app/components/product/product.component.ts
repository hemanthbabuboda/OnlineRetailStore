import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/Product.model';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  products: Product[] = [];
  newProduct: Product = {
    productName: '',
    productDescription: '',
    productPrice: 0
  };
  selectedProduct?: Product;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.getProducts();
  }

  addProduct(): void {
    this.productService.addProduct(this.newProduct).subscribe({
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

  getProduct(productId: number): void {
    this.productService.getProduct(productId).subscribe({
      next: (product) => {
        this.selectedProduct = product;
      },
      error: (error) => {
        console.error('Error fetching product:', error);
      }
    });
  }

  updateProduct(): void {
    if (this.selectedProduct) {
      this.productService.updateProduct(this.selectedProduct.productId!, this.selectedProduct).subscribe({
        next: (product) => {
          console.log('Product updated successfully:', product);
          this.getProducts(); // Refresh the list
        },
        error: (error) => {
          console.error('Error updating product:', error);
        }
      });
    }
  }

  deleteProduct(productId: number): void {
    this.productService.deleteProduct(productId).subscribe({
      next: () => {
        console.log('Product deleted successfully');
        this.products = this.products.filter(p => p.productId !== productId);
      },
      error: (error) => {
        console.error('Error deleting product:', error);
      }
    });
  }

  getProducts(): void {
    this.productService.getProducts().subscribe({
      next: (products) => {
        this.products = products;
      },
      error: (error) => {
        console.error('Error fetching products:', error);
      }
    });
  }

  resetForm(): void {
    this.newProduct = {
      productName: '',
      productDescription: '',
      productPrice: 0
    };
    this.selectedProduct = undefined;
  }
}