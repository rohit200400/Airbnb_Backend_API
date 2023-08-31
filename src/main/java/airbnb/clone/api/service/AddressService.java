package airbnb.clone.api.service;

import airbnb.clone.api.entity.Address;
import airbnb.clone.api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setStreetAddress(updatedAddress.getStreetAddress());
            address.setCity(updatedAddress.getCity());
            address.setState(updatedAddress.getState());
            address.setCountry(updatedAddress.getCountry());
            address.setZipCode(updatedAddress.getZipCode());
            return addressRepository.save(address);
        }
        return null;
    }
}