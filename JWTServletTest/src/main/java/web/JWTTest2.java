package web;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTTest2 {
	private static String secret = "今天是周二";
	private static Algorithm alg = Algorithm.HMAC256(secret);
	public static void main(String[] args) {
		Date issuedAt = new Date();
		String token = JWT.create().withIssuer("小宝")
				.withSubject("是谁呢")
				.withIssuedAt(issuedAt)
				.withExpiresAt(new Date(issuedAt.getTime()+1000L*60))
				.sign(alg);
		System.out.println(token);
		JWTVerifier verifier = JWT.require(alg).withIssuer("小宝").withSubject("是谁呢1").build();
		try {
			verifier.verify(token);
			System.out.println("验证成功");
		} catch (Exception e) {
			System.out.println("验证失败");
		}
	}

}
