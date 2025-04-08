import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/Product.model';

@Component({
  selector: 'app-search-product',
  templateUrl: './search-product.component.html',
  styleUrls: ['./search-product.component.css']
})
export class SearchProductComponent implements OnInit {

  productId?: number;
  products: Product[] = [];
  newProduct: Product = {
    productName: '',
    productDescription: '',
    productPrice: 0
  };
  selectedProduct?: Product;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    // Optionally, you can initialize something here if needed
  }

  getProduct(): void {
    if (this.productId !== undefined) {
      this.productService.getProduct(this.productId).subscribe({
        next: (product) => {
          this.selectedProduct = product;
        },
        error: (error) => {
          console.error('Error fetching product:', error);
        }
      });
    }
  }

  // Optionally, if you want to fetch and display all products
  // getAllProducts(): void {
  //   this.productService.getAllProducts().subscribe({
  //     next: (products) => {
  //       this.products = products;
  //     },
  //     error: (error) => {
  //       console.error('Error fetching products:', error);
  //     }
  //   });
  // }
}
