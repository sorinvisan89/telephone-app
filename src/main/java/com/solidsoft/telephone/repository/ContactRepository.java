package com.solidsoft.telephone.repository;

import com.solidsoft.telephone.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer>, JpaSpecificationExecutor<Contact> {

    Optional<Contact> findById(final Integer id);

    Optional<Contact> findByEmail(final String email);
}
