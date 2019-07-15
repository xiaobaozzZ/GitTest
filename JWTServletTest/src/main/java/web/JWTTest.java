package web;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTTest {
	private static String secret = "xiaobao";
	private static Algorithm alg= Algorithm.HMAC256(secret);
	
	public static void main(String[] args) {
		Date currentTime = new Date();
		String token = JWT.create()
				.withIssuer("bao")//发行者
				.withSubject("小宝")//用户身份标识
				.withIssuedAt(currentTime)//签发时间
				.withExpiresAt(new Date(currentTime.getTime()+1000L*10))//有效期
				.withJWTId("001")//分配的JWT的ID
				.withClaim("PublicClaimExample", "You should not pass")//定义公共域信息
				.sign(alg);
		System.out.println("生成的Token是:"+token);
		JWTVerifier verifier = JWT.require(alg)
				.withIssuer("bao")
				.build();
		try {
			verifier.verify(token);
			System.out.println("验证通过");
		} catch (JWTVerificationException e) {
			e.printStackTrace();
			System.out.println("验证失败");
		}
		
		try {
			DecodedJWT originToken = JWT.decode(token);
			System.out.println("解码得到的发行者是:"+originToken.getIssuer());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("解码得到签发时间是:"+sdf.format(originToken.getIssuedAt()));
			System.out.println("解码得到公共域信息是:"+originToken.getClaim("PublicClaimExample"));
		} catch (JWTVerificationException e) {
			e.printStackTrace();
		}
	}
}
