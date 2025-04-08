export interface Customer {
  customerId?: number,
  customerName: string;
  customerEmail: string;
  customerBillingAddress: CustomerAddress[];  // Change to an array of Address
  customerShippingAddress: CustomerAddress[];  // Change to an array of Address
}

export interface CustomerAddress {
  doorNo: string;
  streetName: string;
  layout: string;
  city: string;
  pincode: string;
}