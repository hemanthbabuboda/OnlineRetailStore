import { Component } from '@angular/core';
import { InventoryService } from '../../services/inventory.service';
import { Inventory } from '../../models/Inventory.model';

@Component({
  selector: 'app-search-inventory',
  templateUrl: './search-inventory.component.html',
  styleUrls: ['./search-inventory.component.css']
})
export class SearchInventoryComponent {
  inventoryId: number | null = null;
  inventory: Inventory | null = null;
  errorMessage: string | null = null;

  constructor(private inventoryService: InventoryService) {}

  searchInventory(): void {
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
}
