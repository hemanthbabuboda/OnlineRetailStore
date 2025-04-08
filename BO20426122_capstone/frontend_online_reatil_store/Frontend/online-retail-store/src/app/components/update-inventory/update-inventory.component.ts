import { Component } from '@angular/core';
import { InventoryService } from '../../services/inventory.service';
import { Inventory } from '../../models/Inventory.model';

@Component({
  selector: 'app-update-inventory',
  templateUrl: './update-inventory.component.html',
  styleUrls: ['./update-inventory.component.css']
})
export class UpdateInventoryComponent {
  inventoryId: number | null = null;
  inventory: Inventory | null = null;
  errorMessage: string | null = null;

  constructor(private inventoryService: InventoryService) {}

  getInventory(): void {
    if (this.inventoryId !== null) {
      this.inventoryService.getInventory(this.inventoryId).subscribe({
        next: (inventory) => {
          this.inventory = inventory;
          this.errorMessage = null;
        },
        error: (error) => {
          console.error('Error fetching inventory:', error);
          this.errorMessage = 'Inventory not found.';
          this.inventory = null;
        }
      });
    }
  }

  updateInventory(): void {
    if (this.inventoryId !== null && this.inventory !== null) {
      this.inventoryService.updateInventory(this.inventoryId, this.inventory).subscribe({
        next: (updatedInventory) => {
          console.log('Inventory updated successfully:', updatedInventory);
          this.errorMessage = null;
          this.inventoryId = null;
          this.inventory = null;
        },
        error: (error) => {
          console.error('Error updating inventory:', error);
          this.errorMessage = 'Error updating inventory.';
        }
      });
    } else {
      this.errorMessage = 'Inventory ID or data is missing.';
    }
  }
}
