export interface LineItem {
    itemId?: number;
    productId: number;
    productName: string;
    quantity: number;
    price: number;
  }
  
  export interface Orders {
    orderId?: number;
    lineItems: LineItem[];
  }