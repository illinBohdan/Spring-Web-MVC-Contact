package org.example.app.service;

import org.example.app.dto.ContactDtoRequest;
import org.example.app.entity.Contact;

import java.util.List;

public interface ContactService {
    boolean create(ContactDtoRequest request);
    List<Contact> fetchAll();
    Contact fetchById(Long id);
    boolean update(Long id, ContactDtoRequest request);
    boolean delete(Long id);
}
