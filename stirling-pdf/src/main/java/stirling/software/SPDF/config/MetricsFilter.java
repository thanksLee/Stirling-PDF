package stirling.software.SPDF.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import stirling.software.common.util.RequestUriUtils;

@Component
@RequiredArgsConstructor
public class MetricsFilter extends OncePerRequestFilter {

    private final MeterRegistry meterRegistry;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (RequestUriUtils.isTrackableResource(request.getContextPath(), uri)) {
            HttpSession session = request.getSession(false);
            String sessionId = (session != null) ? session.getId() : "no-session";
            Counter counter =
                    Counter.builder("http.requests")
                            .tag("session", sessionId)
                            .tag("method", request.getMethod())
                            .tag("uri", uri)
                            .register(meterRegistry);

            counter.increment();
        }

        filterChain.doFilter(request, response);
    }
}
