package org.mongo.web;

import com.google.gson.Gson;
import org.mongo.model.Contact;
import org.mongo.services.BankService;
import org.mongo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mongo.model.Contact.getContactLastName;
import static org.mongo.model.Contact.getContactName;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private BankService bankService;

//    @Resource("contactValidator")
//    private Validator validator;
//
//    @InitBinder
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showAllContacts(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("all");
        modelAndView.addObject("size", pageable.getPageSize());
        modelAndView.addObject("page", pageable.getPageNumber());
        Sort.Order sort = Optional.ofNullable(pageable.getSort()).orElse(new Sort(Sort.Direction.ASC, "name")).iterator().next();
        modelAndView.addObject("sort", sort.getProperty());
        modelAndView.addObject("direction", Sort.Direction.DESC.toString().equals(sort.getDirection().toString()) ? Sort.Direction.ASC.toString() : Sort.Direction.DESC.toString());
        List<Contact.AggregationOptions> options = Arrays.asList(
                new Contact.AggregationOptions("name", "amount", getContactName()),
                new Contact.AggregationOptions("lastName", "amount", getContactLastName()));

        modelAndView.addObject("banks", bankService.getAll());
        modelAndView.addObject("contacts", contactService.getAmountAggregationFor(pageable, contactService.getAll(pageable), options));
        return modelAndView;
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getContacts() {
        return new Gson().toJsonTree(contactService.getAll());
    }

    @RequestMapping(value = "/add/contact", method = RequestMethod.GET)
    public ModelAndView showAddContactForm() {
        return new ModelAndView("add_contact", "contact", Contact.builder().build());
    }

    @RequestMapping(value = "/add/contact", method = RequestMethod.POST)
    public String addContact(@Valid @ModelAttribute("contact") Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_contact";
        }
        if (contact.getId() == null) contactService.add(contact);
        else contactService.update(contact);

        return "redirect:/";
    }

    @RequestMapping(value = "/edit/contact", method = RequestMethod.GET)
    public ModelAndView showEditContactForm(@RequestParam Long id) {
        return new ModelAndView("add_contact", "contact", contactService.get(id));
    }

    @RequestMapping(value = "/delete/contact", method = RequestMethod.GET)
    public String deleteContact(@RequestParam Long id) {
        contactService.remove(id);

        return "redirect:/";
    }

}
