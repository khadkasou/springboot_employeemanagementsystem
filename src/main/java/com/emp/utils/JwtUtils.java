package com.emp.utils;

import com.emp.constants.SecurityConstants;
import com.emp.employee.model.Employee;
import com.emp.user.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {



    public String extractUsernameFromToken(String token) {
        return extractClaimFromToken(token, Claims::getSubject);

    }
    public Date extractExpirationFromToken(String token) {
        return extractClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T extractClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpirationFromToken(token).before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("USER_DETAIL",user);
        return createToken(claims, user.getUsername());
    }
    public String generateTokenForEmployee(Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("EMPLOYEE_DETAIL", employee);
        return createToken(claims, employee.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *60 * 20))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        return !isTokenExpired(token);
    }



    public User getUserDetailsFromJwtToken(String token){
        Claims claims=Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
        ObjectMapper objectMapper= new ObjectMapper();
        try {
            User user =objectMapper.convertValue(claims.get("USER_DETAIL"), User.class);
            return user;
        }catch(Exception e){
            return null;
        }
    }
    public Employee getEmployeeDetailsFromToken(String token){
        Claims claims= Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();

        ObjectMapper objectMapper= new ObjectMapper();
        try{
            Employee employee= objectMapper.convertValue(claims.get("EMPLOYEE_DETAIL"),Employee.class);
            return employee;
        }catch (Exception ex){
            return  null;
        }
    }


}
