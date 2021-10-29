package com.cboot.rpidirector.controllers.utils;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cboot.rpidirector.services.exceptions.TokenException;
import com.cboot.rpidirector.services.user.ValidateTokenUseCase;
import com.cboot.rpidirector.utils.Messages;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RequestAuthorizationFilter implements Filter {

	@Autowired
	private ValidateTokenUseCase validateToken;

	@Autowired
	private Messages messages;

	@Value("${config.authorization.request.filter.enabled}")
	private boolean filterEnabled;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (filterEnabled) {
			HttpServletRequest req = (HttpServletRequest) request;
			String authHeader = req.getHeader("Authorization");
			if (!StringUtils.hasText(authHeader)) {
				log.error("Failed auth, header is empty");
				throw new TokenException(messages.get("exception.invalid.token"));
			}

			try {
				String[] split = new String(Base64.getDecoder().decode(authHeader.substring("Bearer ".length())))
						.split(":");
				if (split.length != 2) {
					log.error("Failed auth, header doesnt have the proper format");
					throw new TokenException(messages.get("exception.invalid.token"));
				}
				if (validateToken.validate(split[0], split[1])) {
					chain.doFilter(request, response);
				} else {
					log.error("Failed auth, token is not valid");
					throw new TokenException(messages.get("exception.invalid.token"));
				}
			} catch (Exception e) {
				log.error("Failed auth, unexpected error", e);
				throw new TokenException(messages.get("exception.invalid.token"));
			}
		} else {
			chain.doFilter(request, response);
		}
	}

}
