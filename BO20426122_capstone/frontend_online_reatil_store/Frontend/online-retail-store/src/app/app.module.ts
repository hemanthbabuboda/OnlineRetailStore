import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CustomerComponent } from './components/customer/customer.component';
import { FormsModule } from '@angular/forms';
import { ProductComponent } from './components/product/product.component';
import { InventoryComponent } from './components/inventory/inventory.component';
import { CartComponent } from './components/cart/cart.component';
import { OrdersComponent } from './components/orders/orders.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';
import { SearchCustomerComponent } from './components/search-customer/search-customer.component';
import { UpdateCustomerComponent } from './components/update-customer/update-customer.component';
import { DeleteCustomerComponent } from './components/delete-customer/delete-customer.component';
import { AddProductComponent } from './components/add-product/add-product.component';
import { SearchProductComponent } from './components/search-product/search-product.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { DeleteProductComponent } from './components/delete-product/delete-product.component';
import { SearchInventoryComponent } from './components/search-inventory/search-inventory.component';
import { UpdateInventoryComponent } from './components/update-inventory/update-inventory.component';
import { DeleteInventoryComponent } from './components/delete-inventory/delete-inventory.component';
import { SearchCartComponent } from './components/search-cart/search-cart.component';
import { UpdateCartComponent } from './components/update-cart/update-cart.component';
import { DeleteCartComponent } from './components/delete-cart/delete-cart.component';
import { DeleteOrderComponent } from './components/delete-order/delete-order.component';
import { UpdateOrderComponent } from './components/update-order/update-order.component';
import { SearchOrderComponent } from './components/search-order/search-order.component';
import { CompositeHomeComponent } from './components/compositeService/composite-home/composite-home.component';
import { AddCustomerCompositeComponent } from './components/compositeService/add-customer-composite/add-customer-composite.component';
import { AddProductCompositeComponent } from './components/compositeService/add-product-composite/add-product-composite.component';
import { AddProductToCartComponent } from './components/compositeService/add-product-to-cart/add-product-to-cart.component';
import { CreateOrderCompositeComponent } from './components/compositeService/create-order-composite/create-order-composite.component';
import { GetOrdersCompositeComponent } from './components/compositeService/get-orders-composite/get-orders-composite.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent,
    ProductComponent,
    InventoryComponent,
    CartComponent,
    OrdersComponent,
    HomeComponent,
    HeaderComponent,
    SearchCustomerComponent,
    UpdateCustomerComponent,
    DeleteCustomerComponent,
    AddProductComponent,
    SearchProductComponent,
    UpdateProductComponent,
    DeleteProductComponent,
    SearchInventoryComponent,
    UpdateInventoryComponent,
    DeleteInventoryComponent,
    SearchCartComponent,
    UpdateCartComponent,
    DeleteCartComponent,
    DeleteOrderComponent,
    UpdateOrderComponent,
    SearchOrderComponent,
    CompositeHomeComponent,
    AddCustomerCompositeComponent,
    AddProductCompositeComponent,
    AddProductToCartComponent,
    CreateOrderCompositeComponent,
    GetOrdersCompositeComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
