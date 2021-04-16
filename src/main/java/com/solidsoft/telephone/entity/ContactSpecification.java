package com.solidsoft.telephone.entity;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;

public class ContactSpecification implements Specification<Contact> {

    private final String freeTextSearchPattern;

    public ContactSpecification(final String freeTextSearchPattern) {
        this.freeTextSearchPattern = freeTextSearchPattern;
    }

    @Override
    public Predicate toPredicate(final Root<Contact> root,
                                 final CriteriaQuery<?> criteriaQuery,
                                 final CriteriaBuilder cb) {

        if (StringUtils.hasText(freeTextSearchPattern)){
            final String toSearch = "%" + freeTextSearchPattern.toLowerCase() + "%";
            final Predicate matches = cb.disjunction();
            matches.getExpressions().addAll(Arrays.asList(
                    cb.like(cb.lower(root.get("name")), toSearch),
                    cb.like(cb.lower(root.get("email")), toSearch),
                    cb.like(cb.lower(root.get("number")), toSearch)
            ));
            return matches;
        }

        return null;
    }
}
