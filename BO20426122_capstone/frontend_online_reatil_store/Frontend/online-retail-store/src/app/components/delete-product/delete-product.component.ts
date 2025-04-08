import { Component } from '@angular/core';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-delete-product',
  templateUrl: './delete-product.component.html',
  styleUrl: './delete-product.component.css'
})
export class DeleteProductComponent {

    productId: number|null = null;
    productName: string| null = null;
    productDescription: string| null = null;
    productPrice: number | null = null;
    successMessage: string | null = null;
  errorMessage: string | null = null;


  constructor(private productService: ProductService) {}

  deleteProduct(){
    if(this.productId!==null){
      this.productService.deleteProduct(this.productId).subscribe({
        next: () => {
          this.successMessage = 'Product deleted successfully.';
          this.errorMessage = null;
        },
        error: (error) => {
          console.error('Error deleting product:', error);
          this.errorMessage = 'Error deleting product.';
          this.successMessage = null;
        }
      });
    }
  }
  // deleteCustomer() {
  //   if (this.customerId !== null) {
  //     this.customerService.deleteCustomer(this.customerId).subscribe({
  //       next: () => {
  //         this.successMessage = 'Customer deleted successfully.';
  //         this.errorMessage = null;
  //       },
  //       error: (error) => {
  //         console.error('Error deleting customer:', error);
  //         this.errorMessage = 'Error deleting customer.';
  //         this.successMessage = null;
  //       }
  //     });
  //   }
  // }
}
