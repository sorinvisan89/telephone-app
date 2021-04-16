package com.solidsoft.telephone.service;

import com.solidsoft.telephone.dto.ContactDTO;
import com.solidsoft.telephone.dto.ContactRequestDTO;
import com.solidsoft.telephone.entity.Contact;
import com.solidsoft.telephone.entity.ContactSpecification;
import com.solidsoft.telephone.mapper.ContactMapper;
import com.solidsoft.telephone.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Autowired
    public ContactService(
            final ContactRepository contactRepository,
            final ContactMapper contactMapper) {
        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    public ContactDTO addContact(final ContactRequestDTO contactRequestDTO) {
        final Contact entity = contactMapper.toEntity(contactRequestDTO);
        checkForExistingEmail(entity.getEmail());

        final Contact saved = contactRepository.save(entity);

        return contactMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<ContactDTO> getContacts() {

        final List<Contact> saved = contactRepository.findAll();

        return saved.stream()
                .map(contactMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ContactDTO getContact(final Integer contactId) {

        final Contact existing = contactRepository.findById(contactId)
                .orElseThrow(buildNotFoundContact(contactId));

        return contactMapper.toDTO(existing);
    }


    @Transactional(readOnly = true)
    public List<ContactDTO> searchContacts(final String freeTextPattern) {

        final ContactSpecification contactSpecification = new ContactSpecification(freeTextPattern);

        final List<Contact> result = contactRepository.findAll(contactSpecification);

        return result.stream()
                .map(contactMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ContactDTO updateContact(final ContactDTO contactDTO) {

        final String newEmail = contactDTO.getEmail();

        final Integer contactId = contactDTO.getId();

        final Contact existing = contactRepository.findById(contactId)
                .orElseThrow(buildNotFoundContact(contactId));

        // For updates on the same entity, no need to check for email duplication on update
        if (!existing.getEmail().equals(newEmail)){
            checkForExistingEmail(newEmail);
        }

        existing.setEmail(contactDTO.getEmail());
        existing.setName(contactDTO.getName());
        existing.setNumber(contactDTO.getNumber());

        // no contactRepository.save() is required for entity because it is in a MANAGED state inside transaction
        return contactMapper.toDTO(existing);
    }

    public ContactDTO deleteContact(final Integer contactId){
        final Contact existing = contactRepository.findById(contactId)
                .orElseThrow(buildNotFoundContact(contactId));

        contactRepository.delete(existing);

        return contactMapper.toDTO(existing);
    }

    private Supplier<EntityNotFoundException> buildNotFoundContact(final Integer contactId) {
        return () -> new EntityNotFoundException(String.format("No contact found for id %d", contactId));
    }

    private void checkForExistingEmail(final String email) {
        contactRepository.findByEmail(email)
                .ifPresent(sameEmailContact -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("Contact with %s email already exists!", email));
                });
    }

}
