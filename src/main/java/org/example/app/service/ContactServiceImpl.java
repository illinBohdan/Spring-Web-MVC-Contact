package org.example.app.service;

import org.example.app.dto.ContactDtoRequest;
import org.example.app.entity.Contact;
import org.example.app.mapper.ContactMapper;
import org.example.app.repository.ContactRepository;
import org.springframework.stereotype.Service;

// @Transactional визначає область дії однієї транзакції БД.
// Транзакція БД відбувається у сфері дій persistence context.
// Persistence context в JPA є EntityManager, який використовує всередині
// клас Session ORM-фреймворку Hibernate.
// Один об'єкт EntityManager може бути використаний кількома транзакціями БД.
//
// EntityManager (Менеджер сутностей):
// - Керує життєвим циклом екземплярів сутностей, включаючи їх створення,
// збереження, пошук та видалення.
// - Дозволяє створювати та виконувати запити за допомогою функціоналу Hibernate.
// - Працює з транзакційним менеджментом, що надається Spring, дозволяючи фіксувати
// та відкатувати транзакції.
//
// https://www.baeldung.com/hibernate-entitymanager
// https://docs.spring.io/spring-framework/reference/data-access/transaction.html
// https://docs.spring.io/spring-framework/reference/data-access/transaction/declarative/annotations.html
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/transaction/annotation/Transactional.html
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactMapper mapper;
    private final ContactRepository repository;

    public ContactServiceImpl(ContactMapper mapper, ContactRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @Transactional
    public boolean create(ContactDtoRequest request) {
        Contact contact = mapper.dtoToEntity(request);
        return repository.create(contact);
    }

    @Override
    @Transactional
    public List<Contact> fetchAll() {
        return repository.fetchAll()
                .orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Contact fetchById(Long id) {
        return repository.fetchById(id)
                .orElse(null);
    }

    @Override
    @Transactional
    public boolean update(Long id, ContactDtoRequest request) {
        Contact contact = mapper.dtoToEntity(request);
        return repository.update(id, contact);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Optional<Contact> optional = repository.fetchById(id);
        return optional.isPresent() && repository.delete(id);
    }
}
