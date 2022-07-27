package self.learning.learningspringboot.criteria;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.ObjectUtils;
import self.learning.learningspringboot.entity.People;
import self.learning.learningspringboot.utils.EntityFieldCheck;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public class PeopleCriteria {

    public static TypedQuery<People> getPeoples(EntityManager em, Map<String, Object> filterParams) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<People> criteriaQuery = builder.createQuery(People.class);
        Root<People> root = criteriaQuery.from(People.class);
        criteriaQuery.select(root);

        //filtering
        if(!ObjectUtils.isEmpty(filterParams.get("search"))){
            String searchText = "%"+filterParams.get("search").toString()+"%";
            Predicate[] searchPredicates = root
                    .getModel()
                    .getDeclaredSingularAttributes()
                    .stream()
                    .filter(a -> a.getBindableJavaType().equals(String.class))
                    .map(a -> builder.like(root.get(a.getName()), searchText))
                    .toArray(Predicate[]::new);

            Predicate searchPredicate = builder.or(searchPredicates);
            criteriaQuery.where(searchPredicate);
        }
        //sorting
        if(filterParams.get("direction").toString().equals("asc") && EntityFieldCheck.isFiledExist(People.class, filterParams.get("sortBy").toString()))
            criteriaQuery.orderBy(builder.asc(root.get(filterParams.get("sortBy").toString())));
        if(filterParams.get("direction").toString().equals("desc") && EntityFieldCheck.isFiledExist(People.class, filterParams.get("sortBy").toString()))
            criteriaQuery.orderBy(builder.desc(root.get(filterParams.get("sortBy").toString())));

        TypedQuery<People> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery;
    }
}
