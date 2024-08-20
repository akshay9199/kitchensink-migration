package com.jboss.migration.kitchensink_migration.service;

import com.jboss.migration.kitchensink_migration.model.Member;
import com.jboss.migration.kitchensink_migration.repository.MemberRepository;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private static final Logger log =  LoggerFactory.getLogger(MemberService.class);

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAllMembersByName() {
        return memberRepository.findAllMembersSortedViaName();
    }

    /**
     * Checks if a member with the same email address is already registered.
     * If doesn't exist insert the member
     *
     * @params Member
     * @return Member
     */
    public Member register(Member member) {
        Member existingMember = memberRepository.findByEmail(member.getEmail());
        if(existingMember != null){
            log.info("Member already exists:{}",existingMember);
            throw new ValidationException("Unique Email Violation");
        }
        log.info("Inserting User:{}",member);
        return memberRepository.insert(member);
    }

    public Member getMemberById(String id) {
        log.info("Details to fetch for Member ID:{}",id);
        return memberRepository.findById(id).orElse(null);
    }
}
