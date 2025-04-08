import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './components/customer/customer.component';
import { ProductComponent } from './components/product/product.component';
import { InventoryComponent } from './components/inventory/inventory.component';
import { CartComponent } from './components/cart/cart.component';
import { OrdersComponent } from './components/orders/orders.component';
import { SearchCustomerComponent } from './components/search-customer/search-customer.component';
import { DeleteCustomerComponent } from './components/delete-customer/delete-customer.component';
import { UpdateCustomerComponent } from './components/update-customer/update-customer.component';
import { SearchProductComponent } from './components/search-product/search-product.component';
import { DeleteProductComponent } from './components/delete-product/delete-product.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { SearchInventoryComponent } from './components/search-inventory/search-inventory.component';
import { DeleteInventoryComponent } from './components/delete-inventory/delete-inventory.component';
import { UpdateInventoryComponent } from './components/update-inventory/update-inventory.component';
import { SearchCartComponent } from './components/search-cart/search-cart.component';
import { DeleteCartComponent } from './components/delete-cart/delete-cart.component';
import { UpdateCartComponent } from './components/update-cart/update-cart.component';
import { SearchOrderComponent } from './components/search-order/search-order.component';
import { DeleteOrderComponent } from './components/delete-order/delete-order.component';
import { UpdateOrderComponent } from './components/update-order/update-order.component';
import { CompositeHomeComponent } from './components/compositeService/composite-home/composite-home.component';
import { AddCustomerCompositeComponent } from './components/compositeService/add-customer-composite/add-customer-composite.component';
import { AddProductCompositeComponent } from './components/compositeService/add-product-composite/add-product-composite.component';
import { AddProductToCartComponent } from './components/compositeService/add-product-to-cart/add-product-to-cart.component';
import { CreateOrderCompositeComponent } from './components/compositeService/create-order-composite/create-order-composite.component';
import { GetOrdersCompositeComponent } from './components/compositeService/get-orders-composite/get-orders-composite.component';

const routes: Routes = [
  { path: 'customer', component : CustomerComponent },
  { path: 'products', component : ProductComponent },
  { path: 'inventory', component : InventoryComponent },
  { path: 'cart', component : CartComponent },
  { path: 'orders', component : OrdersComponent },
  { path: 'searchCustomer', component : SearchCustomerComponent },
  { path: 'deleteCustomer', component : DeleteCustomerComponent },
  { path: 'updateCustomer', component : UpdateCustomerComponent },
  { path: 'searchProduct', component : SearchProductComponent },
  { path: 'deleteProduct', component : DeleteProductComponent },
  { path: 'updateProduct', component : UpdateProductComponent },
  { path: 'searchInventory', component : SearchInventoryComponent },
  { path: 'deleteInventory', component : DeleteInventoryComponent },
  { path: 'updateInventory', component : UpdateInventoryComponent },
  { path: 'searchCart', component : SearchCartComponent },
  { path: 'deleteCart', component : DeleteCartComponent },
  { path: 'updateCart', component : UpdateCartComponent },
  { path: 'searchOrder', component : SearchOrderComponent },
  { path: 'deleteOrder', component : DeleteOrderComponent },
  { path: 'updateOrder', component : UpdateOrderComponent },
  { path: 'composite-home', component : CompositeHomeComponent },
  { path: 'composite-addCustomer', component : AddCustomerCompositeComponent },
  { path: 'composite-addProduct', component : AddProductCompositeComponent },
  { path: 'composite-addProductToCart', component : AddProductToCartComponent },
  { path: 'composite-createOrder', component : CreateOrderCompositeComponent },
  { path: 'composite-getOrders', component : GetOrdersCompositeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
