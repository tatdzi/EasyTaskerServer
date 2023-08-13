package by.taskManager.taskservice.endpoint.web.filter;


import by.TaskManeger.utils.dto.TokenDTO;
import by.TaskManeger.utils.dto.UserDTO;
import by.TaskManeger.utils.dto.UserRole;
import by.taskManager.taskservice.endpoint.handler.JwtTokenHandler;
import by.taskManager.taskservice.service.api.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenHandler jwtHandler;
    private IUserService userService;

    public JwtFilter(JwtTokenHandler jwtHandler,IUserService userService) {
        this.jwtHandler = jwtHandler;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();
        if (!jwtHandler.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        UserDTO user = userService.getInfo(header);
        TokenDTO tokenDTO = new TokenDTO(
                UUID.fromString(user.getUuid()),
                user.getMail(),
                user.getFio(),
                UserRole.valueOf(user.getRole())
        );

        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + tokenDTO.getRole()));

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(tokenDTO, null, roles);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}