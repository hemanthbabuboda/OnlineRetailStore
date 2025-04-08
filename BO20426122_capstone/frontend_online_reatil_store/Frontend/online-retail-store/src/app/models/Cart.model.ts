export interface LineItem {
    itemId?: number;
    productId: number;
    productName: string;
    quantity: number;
    price: number;
  }

  export interface Cart {
    cartId?: number;
    lineItem: LineItem[];
  }