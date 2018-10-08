package org.nirmal.buddy.servlet;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.MDC;

/**
 * Filter that sets request contextual information for the slf4j MDC. Need to create Component for Spring boot
 *
 * <pre>
 * {@code
 *  < Property name="pattern">[%-5level] %d{yyyy-MM-dd_HH:mm:ss.SSS} [${module}] [%t] [%c{1.}] [%M] %X{id} %msg%n< /Property>
 * }
 * </pre>
 *
 * @author nirmal.s
 *
 */
// @Component
// @Order(1)
public class MDCFilter implements Filter {

    public static RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(LETTERS, DIGITS).build();
    private static String ID = "id";
    private static final String prefix = "[" + ID + ":" + generator.generate(4) + "_";
    private static final String postfix = "]";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String id = generateId();
            MDC.put(ID, id);
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public void destroy() {}

    private String generateId() {
        return prefix + System.nanoTime() + postfix;
    }
}
