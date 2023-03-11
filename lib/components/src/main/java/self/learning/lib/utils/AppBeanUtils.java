package self.learning.lib.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Pageable;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public final class AppBeanUtils {

    private AppBeanUtils() {

    }

    public static void copyPropertiesIfNotNull(Object source, Object target,
            String... ignoreProperties) {
        String[] ignored = ArrayUtils.addAll(getNullPropertyNames(source), ignoreProperties);
        BeanUtils.copyProperties(source, target, ignored);
    }

    // source: https://stackoverflow.com/a/5079864
    public static void copySomeProperties(Object source, Object target, Set<String> properties) {
        String[] excludeProperties =
                Arrays.stream(BeanUtils.getPropertyDescriptors(source.getClass()))
                        .map(PropertyDescriptor::getName).filter(name -> !properties.contains(name))
                        .toArray(String[]::new);
        copyPropertiesIfNotNull(source, target, excludeProperties);
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public static List<String> getNullOrEmptyStringPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null
                        || "".equals(wrappedSource.getPropertyValue(propertyName)))
                .collect(Collectors.toList());
    }

    public static <T> T initTargetAndCopyProperties(Object source, Class<T> clazz,
            String... ignoreProperties) {
        try {
            T target = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

	public static String getQueryStringFromObjectsNonNullFields(String urlWithPageable, Object plainObject){
		List<String> ignored = getNullOrEmptyStringPropertyNames(plainObject);
		Class<?> clazz = plainObject.getClass();
        StringBuilder queryBuilder = new StringBuilder();
		/*
		  Assuming all fields are plain primitive or wrapper
		  TODO: We need to introduce list fields support here and
		   also support for non pageable/(? less) string
		 */
		Arrays.stream(clazz.getDeclaredFields()).filter(f -> !ignored.contains(f.getName()))
				.forEach(field -> {
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}
					try {
						Object fieldValue = field.get(plainObject);
						if (fieldValue instanceof List) {
							List<Object> fieldValues = ((List<Object>) fieldValue);
							if(CollectionUtils.isNotEmpty(fieldValues)) {
								List<String> stringValues = fieldValues.stream()
								.map(value -> value.toString()).collect(Collectors.toList());
						queryBuilder.append(
								"&" + field.getName() + "=" + String.join(",", stringValues));
							}
						} else {
							queryBuilder
									.append("&" + field.getName() + "=" + fieldValue.toString());
						}
					} catch (IllegalAccessException e) {
						log.error("This field is not accessible" + field.getName(), e);
					}
				});
		if(queryBuilder.length() == 0){
			return urlWithPageable;
		}
		return urlWithPageable + queryBuilder.toString();
	}

	public static String getQueryStringFromPageable(Pageable pageable) {
		List<String> keyValues = new ArrayList<String>();
		if (pageable.isUnpaged()) {
			keyValues.add("unpaged=true");
		} else {
			keyValues.add(String.format("page=%d", pageable.getPageNumber()));
			keyValues.add(String.format("size=%d", pageable.getPageSize()));
		}

		keyValues.addAll(pageable.getSort().stream().map(order -> {
			return String.format("sort=%s,%s", order.getProperty(), order.getDirection().name());
		}).collect(Collectors.toList()));

		return String.join("&", keyValues);
	}
}
