package com.jboss.migration.kitchensink_migration.controller;

import com.jboss.migration.kitchensink_migration.model.Member;
import com.jboss.migration.kitchensink_migration.service.MemberService;
import com.jboss.migration.kitchensink_migration.utils.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MemberListProducerTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberListProducer memberListProducer;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberListProducer).build();
    }

    @Test
    void testShowFormDefault() throws Exception {
        List<Member> members = Utility.getMembersList();
        given(memberService.findAllMembersByName()).willReturn(members);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("member"))
                .andExpect(model().attributeExists("members"))
                .andExpect(view().name("default"));
    }

    @Test
    void testCreateMember_Success() throws Exception {
        Member member = Utility.getMember();
        given(memberService.register(any(Member.class))).willReturn(member);

        mockMvc.perform(post("/createMember")
                        .param("name", "User 1")
                        .param("email", "user@email.com")
                        .param("phoneNumber", "123456789"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void testWhenRegisterMember_thenRedirect() throws Exception {
        mockMvc.perform(post("/createMember")
                        .param("name", "John Doe")
                        .param("email", "john@example.com")
                        .param("phoneNumber", "1234567890"))
                .andExpect(view().name("default"));
    }
    @Test
    void testCreateMember_Failure() throws Exception {
        BindingResult bindingResult = mock(BindingResult.class);
        given(bindingResult.hasErrors()).willReturn(true);

        mockMvc.perform(post("/createMember")
                        .param("name", "John Doe"))
                .andExpect(status().isOk())
                .andExpect(view().name("default"));
    }
}
