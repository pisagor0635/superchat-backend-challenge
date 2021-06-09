package de.superchat.superchatbackend.services.contact;

import de.superchat.superchatbackend.model.Contact;
import java.util.List;
import java.util.Map;

public interface ContactService {

    List<Contact> getAllContacts();

    Contact createContact(Contact contact);

    Contact getContactById(Long id);

    Contact updateContact(Long id, Contact contactUpdated);

    Map<String,Boolean> deleteContact(Long id);
}
