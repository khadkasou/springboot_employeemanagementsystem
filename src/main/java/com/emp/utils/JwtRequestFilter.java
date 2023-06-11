package com.emp.utils;
import com.emp.employee.model.Employee;
import com.emp.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;


    //    @Qualifier("customUserDetailsService")
//    @Autowired
//    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String requestToken = request.getHeader("Authorization");

        String username= null;
        String jwtToken = null;

        if (requestToken!=null && requestToken.startsWith("Bearer")){
            jwtToken= requestToken.substring(7);

//            username= this.jwtUtils.extractUsernameFromToken(jwtToken);

                User userDetails = jwtUtils.getUserDetailsFromJwtToken(jwtToken);
                if (userDetails!=null){
                    if (jwtUtils.validateToken(jwtToken,userDetails)){

                        UsernamePasswordAuthenticationToken authenticationToken=
                                new UsernamePasswordAuthenticationToken(userDetails,null,
                                        userDetails.getAuthorities());

//                   authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }


           else {
                    Employee employee = jwtUtils.getEmployeeDetailsFromToken(jwtToken);




               if(jwtUtils.validateToken(jwtToken,employee)){

                   UsernamePasswordAuthenticationToken authenticationToken=
                           new UsernamePasswordAuthenticationToken(employee,null,employee.getAuthorities());

//                  authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);
               }
            }
        }
        filterChain.doFilter(request,response);

    }


    }

