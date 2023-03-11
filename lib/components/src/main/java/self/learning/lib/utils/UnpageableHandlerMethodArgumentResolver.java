package self.learning.lib.utils;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UnpageableHandlerMethodArgumentResolver extends PageableHandlerMethodArgumentResolver {

    private static final String DEFAULT_UNPAGED_PARAMETER_NAME = "unpaged";
    private String unpagedParameterName = DEFAULT_UNPAGED_PARAMETER_NAME;

    public UnpageableHandlerMethodArgumentResolver() {
        super();
		super.setMaxPageSize(Integer.MAX_VALUE);
    }

    public void setUnpagedParameterName(String unpagedParameterName) {
        Assert.hasText(unpagedParameterName, "Unpaged parameter name must not be null or empty!");
        this.unpagedParameterName = unpagedParameterName;
    }

    public String getUnpageableParameterName() {
        return this.unpagedParameterName;
    }

    @Override public Pageable resolveArgument(MethodParameter methodParameter,
            @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            @Nullable WebDataBinderFactory binderFactory) {

        String unpaged = webRequest.getParameter(getUnpageableParameterName());
        if (unpaged != null && unpaged.toLowerCase().equals("true")) {
            Pageable pageable =
                    super.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);
            return SortedUnpaged.getInstance(pageable.getSort());
        } else {
            return super.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);
        }
    }
}
