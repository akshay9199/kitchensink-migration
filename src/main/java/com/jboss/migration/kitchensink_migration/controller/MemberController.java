package com.jboss.migration.kitchensink_migration.controller;

import com.jboss.migration.kitchensink_migration.model.Member;
import com.jboss.migration.kitchensink_migration.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private static final Logger log =  LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<Member>> listAllMembers() {
        List<Member> members = memberService.findAllMembersByName();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    //@GetMapping("/{id:[0-9][0-9]*}")
    @GetMapping("/{id}")
    public ResponseEntity<Member> lookupMemberById(@PathVariable String id) {
        Member member = memberService.getMemberById(id);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
