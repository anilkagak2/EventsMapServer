package server;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class PostsFilter
 */
/* Filters all the request coming to the Login.jsp page so that
 * those requests have posts attribute set 
 * */
@WebFilter("/PostsFilter")
public class PostsFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PostsFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("In FilterPosts");
		if (request.getAttribute("posts") == null) 
			request.getRequestDispatcher("/FetchPosts").forward(request, response);
		else
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
