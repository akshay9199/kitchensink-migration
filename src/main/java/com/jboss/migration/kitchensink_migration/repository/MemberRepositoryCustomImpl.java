package com.jboss.migration.kitchensink_migration.repository;

import com.jboss.migration.kitchensink_migration.model.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public MemberRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Member> findAllMembersSortedViaName() {
        Criteria criteria = new Criteria();
        Query query = new Query(criteria);
        query.with(Sort.by(Sort.Direction.ASC, "name"));
        return mongoTemplate.find(query, Member.class);
    }
}
