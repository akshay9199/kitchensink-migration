package com.jboss.migration.kitchensink_migration.service;

import com.jboss.migration.kitchensink_migration.model.Member;
import com.jboss.migration.kitchensink_migration.repository.MemberRepository;
import com.jboss.migration.kitchensink_migration.utils.Utility;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAllMembersByName() {
        List<Member> members = Utility.getMembersList();
        given(memberRepository.findAllMembersSortedViaName()).willReturn(members);

        List<Member> result = memberService.findAllMembersByName();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("User 1", result.get(0).getName());
    }

    @Test
    void testRegister_NewMember() {
        Member newMember = Utility.getMember();
        given(memberRepository.findByEmail("jane@example.com")).willReturn(null);
        given(memberRepository.insert(any(Member.class))).willReturn(newMember);

        Member result = memberService.register(newMember);

        assertNotNull(result);
        assertEquals("User 1", result.getName());
    }

    @Test
    void testRegister_ExistingMember() {
        Member existingMember = Utility.getMember();
        given(memberRepository.findByEmail("user@email.com")).willReturn(existingMember);

        assertThrows(ValidationException.class, () -> {
            memberService.register(existingMember);
        });
    }

    @Test
    void testGetMemberById() {
        Member existingMember = Utility.getMember();
        given(memberRepository.findById("1")).willReturn(Optional.of(existingMember));

        Member result = memberService.getMemberById("1");

        assertNotNull(result);
        assertEquals("user@email.com", result.getEmail());
    }

    @Test
    void testGetMemberById_NotFound() {
        given(memberRepository.findById("999")).willReturn(Optional.empty());

        Member result = memberService.getMemberById("999");

        assertNull(result);
    }
}
