import { Customer } from './Customer.model';
import { Order } from './order.model';

export interface CompleteCustomerOrderDetails {
  customer: Customer;
  orders: Order[];
}
