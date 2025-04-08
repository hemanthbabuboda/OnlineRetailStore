export interface Order {
    orderId: number;
    lineItems: LineItem[];
  }
  
  export interface LineItem {
    itemId: number;
    productId: number;
    productName: string;
    quantity: number;
    price: number;
  }
  