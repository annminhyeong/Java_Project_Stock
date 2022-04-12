package poly.controller;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import poly.service.impl.KakaoService;

@Controller
public class KakaoController {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	//카카오 로그인
	  @Autowired
	  private KakaoService kakao;
	    
	//카카오 로그인
	  @RequestMapping(value="/KakaoLogin",method=RequestMethod.GET) //kalog에 들어갈 값 = Redirect 경로값
	  public String login(@RequestParam("code") String code, HttpSession session) {
		  log.info(this.getClass().getName()+".login start!");
	      String access_Token = kakao.getAccessToken(code);
	      HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
	      System.out.println("login Controller : " + userInfo);
	      
	      //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
	      if (userInfo.get("email") != null) {
	          session.setAttribute("user_id", userInfo.get("email"));
	          session.setAttribute("SS_USER_NAME", userInfo.get("nickname"));
	          session.setAttribute("access_Token", access_Token);
	      }
	      return "/project/user/index"; //리턴은 상관없음
	  }
	  
	// 카카오 로그아웃
	  @RequestMapping(value="/logout")
	  public String logout(HttpSession session) {
	      kakao.kakaoLogout((String)session.getAttribute("access_Token"));
	      session.removeAttribute("access_Token");
	      session.removeAttribute("user_id");
	      session.invalidate();
	      
	      return "/project/LogoutResult";
	  }
}
