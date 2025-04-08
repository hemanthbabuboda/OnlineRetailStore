package com.onlineretailstore.inventory.service;

import java.util.List;

import com.onlineretailstore.inventory.entity.Inventory;

public interface InventoryService {

	public Inventory addInventory(Inventory inventory);
	public void deleteInventory(Integer inventoryId);
	public Inventory updateInventory(Integer inventoryId, Inventory inventory);
	public Inventory getInventory(Integer inventoryId);
	public Inventory getInventoryByProductId(Integer productId);
	public List<Inventory> getAllInventories();
}
