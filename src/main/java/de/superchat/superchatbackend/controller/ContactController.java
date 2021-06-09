package de.superchat.superchatbackend.controller;

import de.superchat.superchatbackend.model.Contact;
import de.superchat.superchatbackend.services.contact.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contact/v1")
@Api(tags = "Contact operations")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/list")
    @ApiOperation(value = "List all contacts - rest api")
    public ResponseEntity<List<Contact>> getAllContacts() {
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @PostMapping("/add")
    @ApiOperation(value = "create contact - rest api")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.createContact(contact));
    }

    @GetMapping("/list/{id}")
    @ApiOperation(value = "get contact by id - rest api")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.getContactById(id));
    }

    @PutMapping("/update/{id}")
    @ApiOperation(value = "update contact - rest api")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contactUpdated) {
        return ResponseEntity.ok(contactService.updateContact(id, contactUpdated));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete contact - rest api")
    public ResponseEntity<Map<String, Boolean>> deleteContact(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.deleteContact(id));
    }
}
