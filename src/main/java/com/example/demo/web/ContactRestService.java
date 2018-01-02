package com.example.demo.web;

import com.example.demo.dao.ContactRepository;
import com.example.demo.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Admin on 19/12/2017.
 */
@RestController
@CrossOrigin("*")
public class ContactRestService {

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/contacts",method = RequestMethod.GET)
    private List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    @RequestMapping(value = "/contacts/{id}",method = RequestMethod.GET)
    private Contact getContact(@PathVariable Long id){
        return contactRepository.findOne(id);
    }

    @RequestMapping(value = "/chercherContacts",method = RequestMethod.GET)
    private Page<Contact> chercher(
            @RequestParam(name = "mc",defaultValue = "") String mc,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "10") int size){
        return contactRepository.chercher("%"+mc+"%",new PageRequest(page,size));
    }

    @RequestMapping(value = "/contacts",method = RequestMethod.POST)
    private Contact save(@RequestBody Contact c){
        return contactRepository.save(c);
    }

    @RequestMapping(value = "/contacts/{id}",method = RequestMethod.DELETE)
    private boolean supprimer(@PathVariable Long id){
        contactRepository.delete(id);
        return true;
    }

    @RequestMapping(value = "/contacts/{id}",method = RequestMethod.PUT)
    private Contact update(@PathVariable Long id,@RequestBody Contact c){
        c.setId(id);
        return contactRepository.save(c);
    }
}
