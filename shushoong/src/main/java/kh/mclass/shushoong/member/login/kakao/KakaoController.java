package kh.mclass.shushoong.member.login.kakao;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class KakaoController {
	
	private final KakaoApi kakaoApi;
	
	@GetMapping("/login/oauth2/code/kakao")
	public String kakaoLogin(String code, HttpSession session) {
		// 1. 인가코드 받기
		
		//2. 토큰 받기
		String accessToken = kakaoApi.getAccessToken(code);
		session.setAttribute("kakaoToken", accessToken);
				
		//3. 사용자 정보 받기
		Map<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);
				
		Object userId = userInfo.get("userId");
		String email = (String) userInfo.get("email");
		String nickname = (String) userInfo.get("nickname");
				
//		System.out.println("userId = "+userId.toString());
//		System.out.println("email = "+email);
//		System.out.println("nickname = "+nickname);
//		System.out.println("accessToken = "+accessToken);
				
		return "redirect:/login";
	}
	
	// 카카오 로그아웃 
	@GetMapping("/logout/kako") 
	public String logout(HttpSession session) {
		String kakaoToken = (String) session.getAttribute("kakaoToken");
		System.out.println("[kakaoToken] = "+kakaoToken);
		if(kakaoToken != null) {
			kakaoApi.unlink(kakaoToken);
			session.invalidate();
			return "redirect:/main";
		}else {
			return "redirect:/login";
		}
	}
	
	//카카오 나한테 링크 보내기
		@GetMapping("/sendmsg/me")
		public String getMethodName(HttpSession session) {
			String kakaoToken = (String) session.getAttribute("kakaoToken");
			sendLinkPost("http://tripant.store/login", "Bearer "+kakaoToken);
			return kakaoToken;
		}
		@PostMapping("https://kapi.kakao.com/v2/api/talk/memo/scrap/send")
		public String sendLinkPost(@RequestBody String request_url, @RequestHeader String Authorization) {
			return "redirect:/login";
		}
}
