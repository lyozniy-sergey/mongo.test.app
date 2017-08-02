package org.mongo.web;

import org.mongo.model.Bank;
import org.mongo.services.BankService;
import org.mongo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author lyozniy.sergey on 17 Jul 2017.
 */
@Controller
public class BankController {
    @Autowired
    private BankService bankService;
    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/banks", method = RequestMethod.GET)
    public ModelAndView showAllBanks(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("all");
        modelAndView.addObject("size", pageable.getPageSize());
        modelAndView.addObject("page", pageable.getPageNumber());
        Sort.Order sort = Optional.ofNullable(pageable.getSort()).orElse(new Sort(Sort.Direction.ASC, "name")).iterator().next();
        modelAndView.addObject("sort", sort.getProperty());
        modelAndView.addObject("direction", Sort.Direction.DESC.toString().equals(sort.getDirection().toString()) ? Sort.Direction.ASC.toString() : Sort.Direction.DESC.toString());

        modelAndView.addObject("contacts", contactService.getAll());
        modelAndView.addObject("banks", bankService.getAll(pageable));
        return modelAndView;
    }

    @RequestMapping(value = "/search/form", method = RequestMethod.POST)
    public ModelAndView searchForm(@ModelAttribute("bank") Bank bank) {
        return new ModelAndView("search_contact");
    }

    @RequestMapping(value = "/search/contact", method = RequestMethod.POST)
    public ModelAndView searchContacts(@RequestParam(value = "criteria") String criteria, @ModelAttribute("bank") Bank bank) {
        ModelAndView modelAndView = new ModelAndView("add_bank", "bank", bank);
        modelAndView.addObject("bank_contacts", contactService.getByPattern(criteria.substring(1)));
        return modelAndView;
    }
    @RequestMapping(value = "/add/contact/bank", method = RequestMethod.POST)
    public ModelAndView addContactToBank(@ModelAttribute("bank") Bank bank) {
        bank.setContacts(contactService.getById(bank.getNewContactIds()));
        bank.setNewContactIds(null);
        return new ModelAndView("add_bank", "bank", bank);
    }

    @RequestMapping(value = "/bank", method = RequestMethod.GET)
    public ModelAndView addBank(HttpServletRequest request, Model model) {
        Bank bank = new Bank();
        ModelAndView view = new ModelAndView("add_bank", "bank", bank);
        request.getSession().setAttribute("bank", bank);
        model.addAttribute("bank", bank);
        view.addObject(bank);
        return view;
    }

    @RequestMapping(value = "/add/bank", method = RequestMethod.POST)
    public String addBank(@ModelAttribute("bank") Bank bank) {
        if((bank.getContacts() == null || bank.getContacts().isEmpty()) && (bank.getNewContactIds() !=null && !bank.getNewContactIds().isEmpty())){
            bank.setContacts(contactService.getById(bank.getNewContactIds()));
            bank.setNewContactIds(null);
        }
        if (bank.getId() == null) bankService.add(bank);
        else bankService.update(bank);

        return "redirect:/";
    }

    @RequestMapping(value = "/edit/bank", method = RequestMethod.GET)
    public ModelAndView showEditForm(@RequestParam Long id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("add_bank", "bank", bankService.get(id));
        // set session scoped attribute
        HttpSession session = request.getSession();
        session.setAttribute("name", "session scoped attribute");
        // set request scoped attribute
        request.setAttribute("name", "request scoped attribute");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/bank", method = RequestMethod.GET)
    public String deleteContact(@RequestParam Long id) {
        bankService.remove(id);

        return "redirect:/";
    }

}
