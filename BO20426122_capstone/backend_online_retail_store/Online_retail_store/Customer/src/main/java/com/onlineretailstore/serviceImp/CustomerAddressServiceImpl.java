package com.onlineretailstore.serviceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineretailstore.entity.CustomerAddress;
import com.onlineretailstore.exception.AddressNotFoundException;
import com.onlineretailstore.exception.InvalidAddressDataException;
import com.onlineretailstore.repository.CustomerAddressRepository;
import com.onlineretailstore.service.CustomerAddressService;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerAddressServiceImpl.class);

    @Autowired
    private CustomerAddressRepository addressRepo;

    @Override
    public CustomerAddress addCustomerAddress(CustomerAddress address) throws InvalidAddressDataException {
        try {
            // Validate the address
            if (address.getStreetName() == null || address.getCity() == null || address.getPincode() == null) {
                logger.warn("Invalid address data: {}", address);
                throw new InvalidAddressDataException("Street name, city, and pincode must not be null.");
            }

            logger.info("Adding customer address for door number: {}", address.getDoorNo());
            CustomerAddress savedAddress = addressRepo.save(address);
            logger.info("Customer address added with ID: {}", savedAddress.getAddressId());
            return savedAddress;
        } catch (InvalidAddressDataException e) {
            logger.error("Invalid address data provided: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while adding customer address: {}", e.getMessage());
            throw new RuntimeException("Error adding customer address", e);
        }
    }

    @Override
    public void deleteCustomerAddress(int addressId) throws AddressNotFoundException {
        try {
            logger.info("Deleting customer address with ID: {}", addressId);
            if (!addressRepo.existsById(addressId)) {
                logger.warn("Address with ID: {} not found for deletion", addressId);
                throw new AddressNotFoundException("Address not found with ID: " + addressId);
            }
            addressRepo.deleteById(addressId);
            logger.info("Customer address with ID: {} deleted successfully", addressId);
        } catch (AddressNotFoundException e) {
            logger.error("Address not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting customer address: {}", e.getMessage());
            throw new RuntimeException("Error deleting customer address", e);
        }
    }

    @Override
    public CustomerAddress updateCustomerAddress(int addressId, CustomerAddress address) 
            throws AddressNotFoundException, InvalidAddressDataException {
        try {
            // Validate the address
            if (address.getStreetName() == null || address.getCity() == null || address.getPincode() == null) {
                logger.warn("Invalid address data for ID: {}", addressId);
                throw new InvalidAddressDataException("Street name, city, and pincode must not be null.");
            }

            logger.info("Updating customer address with ID: {}", addressId);
            CustomerAddress existingAddress = searchCustomerAddress(addressId);  // This method already has logging

            // Update the address details
            existingAddress.setCity(address.getCity());
            existingAddress.setDoorNo(address.getDoorNo());
            existingAddress.setLayout(address.getLayout());
            existingAddress.setStreetName(address.getStreetName());
            existingAddress.setPincode(address.getPincode());

            CustomerAddress updatedAddress = addressRepo.save(existingAddress);
            logger.info("Customer address with ID: {} updated successfully", updatedAddress.getAddressId());
            return updatedAddress;
        } catch (AddressNotFoundException | InvalidAddressDataException e) {
            logger.error("Error during address update: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while updating customer address: {}", e.getMessage());
            throw new RuntimeException("Error updating customer address", e);
        }
    }

    @Override
    public CustomerAddress searchCustomerAddress(int addressId) throws AddressNotFoundException {
        try {
            logger.info("Searching for customer address with ID: {}", addressId);
            return addressRepo.findById(addressId)
                    .orElseThrow(() -> {
                        logger.warn("Address with ID: {} not found", addressId);
                        return new AddressNotFoundException("Address not found with ID: " + addressId);
                    });
        } catch (AddressNotFoundException e) {
            logger.error("Address not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while searching for customer address: {}", e.getMessage());
            throw new RuntimeException("Error searching for customer address", e);
        }
    }
}
