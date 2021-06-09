package de.superchat.superchatbackend.services.contact.impl;

import de.superchat.superchatbackend.exception.ResourceNotFoundException;
import de.superchat.superchatbackend.model.Contact;
import de.superchat.superchatbackend.repository.ContactRepository;
import de.superchat.superchatbackend.services.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id : " + id));
    }

    @Override
    public Contact updateContact(Long id, Contact contactUpdated) {

        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id : " + id));

        contact.setFirstName(contactUpdated.getFirstName());
        contact.setLastName(contactUpdated.getLastName());
        contact.setEmail(contactUpdated.getEmail());
        contact.setPhoneNumber(contactUpdated.getPhoneNumber());

        contact = contactRepository.save(contact);

        return contact;
    }

    @Override
    public Map<String, Boolean> deleteContact(Long id) {

        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id : " + id));
        contactRepository.delete(contact);
        Map<String ,Boolean> response = new HashMap<>();
        response.put("deleted",true);

        return response;
    }
}
