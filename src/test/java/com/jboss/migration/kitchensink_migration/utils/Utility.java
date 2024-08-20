package com.jboss.migration.kitchensink_migration.utils;

import com.jboss.migration.kitchensink_migration.model.Member;

import java.util.List;

public class Utility {

    public static List<Member> getMembersList(){
       return List.of(
               Member.builder().id("1").name("User 1")
                       .email("user@email.com").build()
               ,Member.builder().id("2").name("User 1")
                       .email("test@email.com").build());
    }
    public static Member getMember(){
        return Member.builder().id("1").name("User 1")
                        .email("user@email.com").phoneNumber("123456789").build();
    }
}
