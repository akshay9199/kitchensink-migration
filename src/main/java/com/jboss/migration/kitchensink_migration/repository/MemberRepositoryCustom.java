package com.jboss.migration.kitchensink_migration.repository;

import com.jboss.migration.kitchensink_migration.model.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findAllMembersSortedViaName();
}
