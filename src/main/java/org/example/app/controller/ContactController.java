package org.example.app.controller;

import org.example.app.dto.ContactDtoRequest;
import org.example.app.entity.Contact;
import org.example.app.service.ContactService;
import org.springframework.stereotype.Controller;
// Interface Model - інтерфейс, який визначає власника
// атрибутів моделі.
// Model може надавати атрибути, які використовуються
// для візуалізації представлень (views).
// Дані, які розміщуються в цих моделях, використовуються
// представленням (view) – як правило, шаблонним представленням
// (templated view) для візуалізації веб-сторінки.
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/ui/Model.html
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// Class RedirectView - View, який переспрямовує на абсолютну
// або контекстну відносну URL-адресу.
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/reactive/result/view/RedirectView.html
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping("/contacts")
    public String fetchAllContacts(Model model) {
        List<Contact> list = service.fetchAll();
        model.addAttribute("title", "Contacts");
        if (list.isEmpty()) {
            model.addAttribute("contactsInfo", "No Contacts yet :(");
        } else {
            model.addAttribute("contacts", list);
        }
        model.addAttribute("fragmentName", "contact-list");
        return "layout";
    }

    @RequestMapping("/create-contact")
    public String createContact(Model model) {
        model.addAttribute("title", "Add Contact");
        model.addAttribute("fragmentName", "contact-add");
        return "layout";
    }

    @RequestMapping(value = "/add-contact", method = RequestMethod.POST)
    public RedirectView addContact(@ModelAttribute ContactDtoRequest request) {
        RedirectView redirectView = new RedirectView("./contacts");
        if (service.create(request))
            return redirectView;
        else return redirectView;
    }

    @RequestMapping("/update-contact/{id}")
    public String updateContact(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Update Contact");
        Contact contact = service.fetchById(id);
        model.addAttribute("contact", contact);
        model.addAttribute("fragmentName", "contact-update");
        return "layout";
    }

    @RequestMapping(value = "/change-contact", method = RequestMethod.POST)
    public RedirectView changeContact(@ModelAttribute ContactDtoRequest request) {
        RedirectView redirectView = new RedirectView("./contacts");
        if (service.update(request.id(), request))
            return redirectView;
        else return redirectView;
    }

    @RequestMapping("/delete-contact/{id}")
    public RedirectView deleteContact(@PathVariable("id") Long id) {
        RedirectView redirectView = new RedirectView("../contacts");
        if (service.delete(id)) return redirectView;
        else return redirectView;
    }
}
