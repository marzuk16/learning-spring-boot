package com.learning.Learningspringboot.repository.custom.implementation;

import com.learning.Learningspringboot.entity.BaseEntity;
import com.learning.Learningspringboot.entity.People;
import com.learning.Learningspringboot.enums.RecordStatus;
import com.learning.Learningspringboot.repository.custom.PeopleCustomRepository;
import com.learning.Learningspringboot.utils.Utils;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PeopleCustomRepositoryImpl implements PeopleCustomRepository {
    public final EntityManager em;

    @Override
    public List<People> getList(Integer page, Integer size, String sortBy, RecordStatus status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();

        Root<People> peopleRoot = cq.from(People.class);
        cq.select(peopleRoot);

        Field[] fields = People.class.getDeclaredFields();
        Field[] baseFields = BaseEntity.class.getDeclaredFields();

        if(Utils.checkColumnName(fields, sortBy) || Utils.checkColumnName(baseFields, sortBy)){
            String[] sort = sortBy.split(",");
            if(sort.length > 1 && (sort[1].charAt(0) == 'd' || sort[1].charAt(0) == 'D'))
                    cq.orderBy(cb.desc(peopleRoot.get(sort[0])));
            else
                cq.orderBy(cb.asc(peopleRoot.get(sort[0])));
        }

        List<Predicate> predicates = new ArrayList<>();

        if(status != null) {
//            Predicate namePredicate = cb.like(peopleRoot.get("name"), "%" + name + "%");
            Predicate statusPredicate = cb.equal(peopleRoot.get("recordStatus"), status);
            Predicate phonePredicate = cb.like(peopleRoot.get("phone"), "8" + "%");
            predicates.add(statusPredicate);
            predicates.add(phonePredicate);
        }

        cq.where(predicates.toArray(new Predicate[]{}));
        TypedQuery<People> query = em.createQuery(cq);
//        TypedQuery<People> query = em.createQuery(cq).setFirstResult((page*size)-1).setMaxResults(size);

        return query.getResultList();
    }

    @Override public List<People> getAll(String sortBy) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();

        Root<People> peopleRoot = cq.from(People.class);
        cq.select(peopleRoot);

        Field[] fields = People.class.getDeclaredFields();

        if(Utils.checkColumnName(fields, sortBy)){
            String[] sort = sortBy.split(",");
            if(sort.length > 1 && (sort[1].charAt(0) == 'd' || sort[1].charAt(0) == 'D') )
                    cq.orderBy(cb.desc(peopleRoot.get(sort[0])));
            else
                cq.orderBy(cb.asc(peopleRoot.get(sort[0])));
        }

        TypedQuery<People> query = em.createQuery(cq);

        return query.getResultList();
    }
}
