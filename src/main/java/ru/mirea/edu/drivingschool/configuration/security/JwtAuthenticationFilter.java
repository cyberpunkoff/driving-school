package ru.mirea.edu.drivingschool.configuration.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.mirea.edu.drivingschool.repository.TokenRepository;
import ru.mirea.edu.drivingschool.service.JwtService;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final int BEGIN_INDEX = 7;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if (isAuthPathRequested(request) || !isTokenPresent(authHeader)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(BEGIN_INDEX);
        final String userEmail = jwtService.extractUsername(jwt);

        if (userEmail != null && userNeedsAuthentication()) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (isTokenValid(jwt, userDetails)) {
                setAuthToken(request, userDetails);
            }
        }
        filterChain.doFilter(request, response);
    }

    private static void setAuthToken(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private boolean isTokenValid(String jwt, UserDetails userDetails) {
        var isTokenNonExpiredAndNonRevoked = tokenRepository.findByToken(jwt)
            .map(t -> !t.isExpired() && !t.isRevoked())
            .orElse(false);

        return jwtService.isTokenValid(jwt, userDetails) && isTokenNonExpiredAndNonRevoked;
    }

    private static boolean userNeedsAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private static boolean isTokenPresent(String authHeader) {
        return authHeader != null && authHeader.startsWith("Bearer ");
    }

    private static boolean isAuthPathRequested(HttpServletRequest request) {
        return request.getServletPath().contains("/api/v1/auth");
    }
}
