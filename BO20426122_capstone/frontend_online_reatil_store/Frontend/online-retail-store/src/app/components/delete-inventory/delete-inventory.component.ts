import { Component } from '@angular/core';
import { InventoryService } from '../../services/inventory.service';

@Component({
  selector: 'app-delete-inventory',
  templateUrl: './delete-inventory.component.html',
  styleUrls: ['./delete-inventory.component.css']
})
export class DeleteInventoryComponent {
  inventoryId: number | null = null;
  errorMessage: string | null = null;

  constructor(private inventoryService: InventoryService) {}

  deleteInventory(): void {
    if (this.inventoryId !== null) {
      this.inventoryService.deleteInventory(this.inventoryId).subscribe({
        next: () => {
          console.log('Inventory deleted successfully');
          this.inventoryId = null;
          this.errorMessage = null;
        },
        error: (error) => {
          console.error('Error deleting inventory:', error);
          this.errorMessage = 'Error deleting inventory.';
        }
      });
    } else {
      this.errorMessage = 'Inventory ID is missing.';
    }
  }
}
