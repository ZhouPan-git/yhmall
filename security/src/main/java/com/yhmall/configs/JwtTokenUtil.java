package com.yhmall.configs;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhmall.bean.User;
import com.yhmall.dao.UserDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Nick
 * @Classname JwtTokenUtil
 * @Date 2023/08/20 20:58
 * @Description 创建和验证token
 */
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret ="javainuse";

	@Autowired
	private UserDao userDao;

	public static void main(String[] args) {
		JwtTokenUtil jtk = new JwtTokenUtil();
		UserDetails ud = new UserDetails(){

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return null;
			}

			@Override
			public String getPassword() {
				return "$2a$10$EofLHpl24mr6s1SxK/NikOcSAEV8854F83wufbhXIQY41pFX/4n36";
			}

			@Override
			public String getUsername() {
				return "a";
			}

			@Override
			public boolean isAccountNonExpired() {
				return false;
			}

			@Override
			public boolean isAccountNonLocked() {
				return false;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return false;
			}

			@Override
			public boolean isEnabled() {
				return true;
			}
		};
		String token = jtk.generateToken(ud);
		System.out.println(token);

		System.out.println(new BCryptPasswordEncoder().encode("a"));

	}

	/**
	 *
	 * @param userDetails SpringSecurity提供的的用户类,包装登录信息
	 * @return
	 */
	//generate token for user
	public String generateToken(UserDetails userDetails) {
		//存载荷 7个默认值
		Map<String, Object> claims = new HashMap<>();
		//自己增加的载荷
		claims.put("username", userDetails.getUsername());
		claims.put("pwd", userDetails.getPassword());

		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("username",userDetails.getUsername());
		wrapper.eq("pwd", userDetails.getPassword());

		// Resuser resuser = this.resuserDao.selectOne(wrapper);
		// claims.put("userid", resuser.getUserid());
		claims.put("userid", 1);
		return doGenerateToken(claims, userDetails.getUsername());
	}

	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder()
				//有效载荷
				.setClaims(claims)
				//主题
				.setSubject(subject)
				//token发布时间
				.setIssuedAt(new Date(System.currentTimeMillis()))
				//token有效期
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				//签名
				.signWith(SignatureAlgorithm.HS512, secret)
				//压缩
				.compact();
	}


	//retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	//从token中获取claim
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	//for retrieveing any information from token we will need the secret key
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	//check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	//validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}