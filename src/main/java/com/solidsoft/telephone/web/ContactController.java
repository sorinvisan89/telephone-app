package com.solidsoft.telephone.web;

import com.solidsoft.telephone.dto.ContactDTO;
import com.solidsoft.telephone.dto.ContactRequestDTO;
import com.solidsoft.telephone.dto.ContactSearchDTO;
import com.solidsoft.telephone.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@Slf4j
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(final ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/index")
    public String indexPage(final Model model) {
        final List<ContactDTO> result = contactService.getContacts();
        model.addAttribute("contacts", result);
        return "index";
    }

    // Add functionality

    @GetMapping("/signup")
    public String showSignUpForm(@ModelAttribute("contact") final ContactRequestDTO contactRequestDTO) {
        return "add-contact";
    }

    @PostMapping("/contact")
    public String addContact(
            @ModelAttribute("contact") final @Valid @NotNull ContactRequestDTO contactRequestDTO,
            final BindingResult result,
            final Model model) {

        if (result.hasErrors()) {
            return "add-contact";
        }

        final ContactDTO saved = contactService.addContact(contactRequestDTO);
        log.info("Saved contact {} ", saved);

        return "redirect:/index";
    }

    // Edit functionality

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") final Integer contactId, Model model) {
        final ContactDTO existing = contactService.getContact(contactId);
        model.addAttribute("contact", existing);

        return "update-contact";
    }

    // PostMapping is needed for the Thymeleaf engine for the form, would normally have a PutMapping
    @PostMapping("/contact/{contactId}")
    public String updateContact(
            @PathVariable("contactId") final Integer contactId,
            @ModelAttribute("contact") @Valid @NotNull final ContactDTO contactDTO,
            final BindingResult result,
            final Model model) {

        contactDTO.setId(contactId);

        if (result.hasErrors()) {
            return "update-contact";
        }

        final ContactDTO updated = contactService.updateContact(contactDTO);
        log.info("Updated contact {} ", updated);

        return "redirect:/index";
    }

    // Search functionality

    @GetMapping("/search")
    public String ShowSearchForm(@ModelAttribute("searchText") final ContactSearchDTO contactSearchDTO) {
        return "search-contact";
    }

    @PostMapping("/contacts/filter")
    public String filterContacts(
            @ModelAttribute("searchText") @Valid @NotNull final ContactSearchDTO contactSearchDTO,
            final BindingResult result,
            final Model model) {

        if (result.hasErrors()) {
            return "search-contact";
        }

        final List<ContactDTO> contacts = contactService.searchContacts(contactSearchDTO.getSearchString());
        model.addAttribute("contacts", contacts);

        return "search-contact";
    }

    // Delete functionality

    // GetMapping is needed for the Thymeleaf engine, would normally have a DeleteMapping
    @GetMapping("/contact/delete/{id}")
    public String deleteContact(
            @PathVariable("id") final Integer contactId,
            final Model model) {

        final ContactDTO result = contactService.deleteContact(contactId);
        log.info("Deleted contact {} ", result);

        return "redirect:/index";
    }
}
