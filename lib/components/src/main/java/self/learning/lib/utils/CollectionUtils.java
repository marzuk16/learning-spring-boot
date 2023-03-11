package self.learning.lib.utils;

import java.util.List;
import java.util.function.Function;

public final class CollectionUtils {
	public static <T> Double getDoubleTotal(List<T> dtoList, Function<T, Double> mapper) {
		return dtoList.stream().map(mapper).reduce(Double::sum).orElse(0.0);
	}

	public static <T> Long getLongTotal(List<T> dtoList, Function<T, Long> mapper) {
		return dtoList.stream().map(mapper).reduce(Long::sum).orElse(0L);
	}
}
