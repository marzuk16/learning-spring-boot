package self.learning.lib.utils;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.compile.RenderingContext;
import org.hibernate.query.criteria.internal.expression.LiteralExpression;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import java.util.List;

public final class SpecificationUtils {

    public static <T> List<T> findAll(EntityManager entityManager, Specification<T> specs,
            Class clazz) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);

        Predicate predicate = specs.toPredicate(root, criteriaQuery, criteriaBuilder);

        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

	public static LiteralExpression<String> getLiteralExpression(CriteriaBuilder builder, String literal) {
		return new LiteralExpression<String>
				((CriteriaBuilderImpl) builder, literal){
			@Override public String render (RenderingContext renderingContext){
				return getLiteral();
			}
		};
	}

	@SuppressWarnings("unchecked")
	public static <X, Y, Z> Join<Y, Z> join(From<X, Y> root, Attribute<? super Y, ?> attribute,
			JoinType joinType) {

		return (Join<Y, Z>) root.getJoins().stream()
				.filter(join -> join.getAttribute().getName().equals(attribute.getName())
						&& join.getJoinType().equals(joinType))
				.findFirst().orElseGet(() -> {					
					return root.join(attribute.getName(), joinType);
				});
	}
}
