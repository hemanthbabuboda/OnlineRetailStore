import { Component } from '@angular/core';
import { InventoryService } from '../../services/inventory.service';
import { Inventory } from '../../models/Inventory.model';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css'],
})
export class InventoryComponent {
  inventories: Inventory[] = [];
  newInventory: Inventory = {
    productId: 0,
    quantity: 0,
  };
  selectedInventory: Inventory | null = null;

  constructor(private inventoryService: InventoryService) {}

  addInventory() {
    this.inventoryService.addInventory(this.newInventory).subscribe({
      next: (inventory) => {
        console.log('Inventory added successfully:', inventory);
        this.inventories.push(inventory);
        this.resetForm();
      },
      error: (error) => {
        console.error('Error adding inventory:', error);
      },
    });
  }

  getInventory(id: number) {
    this.inventoryService.getInventory(id).subscribe({
      next: (inventory) => {
        this.selectedInventory = inventory;
        console.log('Inventory fetched successfully:', inventory);
      },
      error: (error) => {
        console.error('Error fetching inventory:', error);
      },
    });
  }

  updateInventory() {
    if (this.selectedInventory && this.selectedInventory.inventoryId) {
      this.inventoryService
        .updateInventory(this.selectedInventory.inventoryId, this.selectedInventory)
        .subscribe({
          next: (updatedInventory) => {
            console.log('Inventory updated successfully:', updatedInventory);
            this.resetForm();
          },
          error: (error) => {
            console.error('Error updating inventory:', error);
          },
        });
    }
  }

  deleteInventory(id: number) {
    this.inventoryService.deleteInventory(id).subscribe({
      next: () => {
        console.log('Inventory deleted successfully');
        this.inventories = this.inventories.filter((inv) => inv.inventoryId !== id);
      },
      error: (error) => {
        console.error('Error deleting inventory:', error);
      },
    });
  }

  resetForm() {
    this.newInventory = {
      productId: 0,
      quantity: 0,
    };
    this.selectedInventory = null;
  }
}

