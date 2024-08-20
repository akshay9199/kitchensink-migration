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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    void testListAllMembers() throws Exception {
          List<Member> members = Utility.getMembersList();

        given(memberService.findAllMembersByName()).willReturn(members);

        mockMvc.perform(get("/members")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(members.size()))
                .andExpect(jsonPath("$[0].id").value(members.get(0).getId()))
                .andExpect(jsonPath("$[1].id").value(members.get(1).getId()));
    }

    @Test
    void testLookupMemberById_Found() throws Exception {
        Member member = Utility.getMember();

        given(memberService.getMemberById("1")).willReturn(member);

        mockMvc.perform(get("/members/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(member.getId()))
                .andExpect(jsonPath("$.name").value(member.getName()));
    }

    @Test
    void testLookupMemberById_NotFound() throws Exception {
        given(memberService.getMemberById("999")).willReturn(null);

        mockMvc.perform(get("/members/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}