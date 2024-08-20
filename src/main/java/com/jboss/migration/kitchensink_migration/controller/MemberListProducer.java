package com.jboss.migration.kitchensink_migration.controller;

import com.jboss.migration.kitchensink_migration.exception.GlobalExceptionHandler;
import com.jboss.migration.kitchensink_migration.model.Member;
import com.jboss.migration.kitchensink_migration.service.MemberService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberListProducer {
    private static final Logger logger =  LoggerFactory.getLogger(GlobalExceptionHandler.class);



    private final MemberService memberService;

    public MemberListProducer(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String showFormDefault(Model model) {
        model.addAttribute("member", Member.builder().build());
        model.addAttribute("members", memberService.findAllMembersByName());
        logger.info("Member details: {}", model.getAttribute("member"));
        return "default";
    }

    /**
     * Creates a new member from the values provided. Performs validation,
     * or with a map of fields, and related errors.
     */
    @PostMapping("/createMember")
    public String createMember( @Valid @ModelAttribute("member") Member member, BindingResult bindingResult,
                               Model model)  {
        if (bindingResult.hasErrors()) {
            model.addAttribute("members", memberService.findAllMembersByName());
            return "default";
        }
        Member createdMember = memberService.register(member);
        model.addAttribute("message", "Form submitted successfully!");
        model.addAttribute("member", Member.builder().build());
        model.addAttribute("members", memberService.findAllMembersByName());
        return "default";
    }

}