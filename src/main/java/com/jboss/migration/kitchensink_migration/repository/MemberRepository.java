package com.jboss.migration.kitchensink_migration.repository;

import com.jboss.migration.kitchensink_migration.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, String>,MemberRepositoryCustom {

    @Query("{email:'?0'}")
    Member findByEmail(String email);
}