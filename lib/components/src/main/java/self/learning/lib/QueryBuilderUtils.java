package self.learning.lib;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Parameter;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class QueryBuilderUtils {

    private QueryBuilderUtils() {}

    public static final String BASE_WHERE_CLAUSE = " WHERE 1=1 ";
    public static final String ORDER_BY_CLAUSE = " ORDER BY ";

    public static void addWhereClause(StringBuilder whereQueryBuilder, String queryClause,
            Map<String, String> filterParams, String... parameters) {

        if (whereQueryBuilder == null || MapUtils.isEmpty(filterParams)
                || ArrayUtils.isEmpty(parameters))
            return;

        boolean allMatch = Arrays.stream(parameters).allMatch(filterParams::containsKey);
        if (allMatch) {
            String clause = String.format(" %s ", queryClause.trim());
            whereQueryBuilder.append(clause);
        }

    }

	public static void addWhereClause(StringBuilder whereQueryBuilder, String queryClause) {

		if(whereQueryBuilder == null || queryClause == null) {
			return;
		}

		String clause = String.format(" %s ", queryClause.trim());
		whereQueryBuilder.append(clause);
	}

    public static void setParameters(Query query, Map<String, String> filterParameters) {

        Set<Parameter<?>> parameters = query.getParameters();
        parameters.stream().forEach(parameter -> {
            Optional<Object> value = castValueToListOrSingle(filterParameters.get(parameter.getName()));
            value.ifPresent(val -> {
                query.setParameter(parameter.getName(), val);
            });
        });
    }

    public static void setSorting(StringBuilder query, Pageable page, String defaultOrder) {
        if (StringUtils.isBlank(defaultOrder))
            throw new RuntimeException("No default order has been provided");

        Sort sort = page.getSort();
        query.append(ORDER_BY_CLAUSE);
        // addDefaultOrderIfSortEmpty(query, sort, defaultOrder);
        if (sort.isEmpty()) {
            query.append(defaultOrder);
            return;
        }
        sort.forEach(order -> {
            query.append(self.learning.lib.utils.StringUtils.camelToSnake(order.getProperty()))
                    .append(StringUtils.SPACE).append(order.getDirection().name() + ",");
        });
        query.deleteCharAt(query.length() - 1);
    }

    public static void setPagination(Query query, Pageable page) {
        if (page.isPaged()) {
            int pageNumber = page.getPageNumber();
            int pageSize = page.getPageSize();
            query.setFirstResult((pageNumber) * pageSize);
            query.setMaxResults(pageSize);
        }
    }

    private static Optional<Object> castValueToListOrSingle(String parameter) {

        if (parameter == null)
            return Optional.ofNullable(null);
        if (parameter.contains(","))
            return Optional.of(Arrays.asList(parameter.split(",")));
        return Optional.of(parameter);
    }
}
