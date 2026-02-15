package org.example.app.mapper;

import org.example.app.dto.ContactDtoRequest;
import org.example.app.entity.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    // Мапить запит (request) до сутності (entity)
    public Contact dtoToEntity(ContactDtoRequest request) {
        Contact contact = new Contact();
        contact.setFirstName(request.firstName());
        contact.setLastName(request.lastName());
        contact.setPhone(request.phone());
        return contact;
    }
}
