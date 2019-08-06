package kr.pethub.core.authority;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;



/**
 * Argument 사용자 정보 조회
 */
public class UserHandlerMethodArgumentResolver  implements HandlerMethodArgumentResolver{
	

	public boolean supportsParameter(MethodParameter parameter) {
		return Auth.class.isAssignableFrom(parameter.getParameterType());
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return AuthUtil.getUser();
	}

}