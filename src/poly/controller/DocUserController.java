package poly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.DocUserDTO;
import poly.dto.MailDTO;
import poly.dto.PagingDTO;
import poly.dto.SearchDTO;
import poly.service.IDocUserService;
import poly.service.IMailService;
import poly.util.CmmUtil;
import poly.util.EncryptUtill;

@Controller
public class DocUserController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "DocUserService")
	private IDocUserService docuserservice;

	@Resource(name = "MailService")
	private IMailService mailService;

	// 인덱스
	@RequestMapping(value = "project/user/index")
	public String index() {
		return "/project/user/index";
	}

	// 로그인
	@RequestMapping(value = "project/login")
	public String login() {
		return "/project/login";
	}

	// 로그인 체크
	@RequestMapping(value = "project/getUserLoginCheck")
	public String getUserLoginCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {

		log.info(this.getClass().getName() + "getUserLoginCheck Start!");

		int res = 0;

		DocUserDTO pDTO = null;

		try {

			String id = CmmUtil.nvl(request.getParameter("id"));
			String password = CmmUtil.nvl(request.getParameter("password"));

			log.info("id:" + id);
			log.info("password:" + password);

			// 웹에서 받은 정보를 저장할 변수를 메모레에 올리기
			pDTO = new DocUserDTO();

			pDTO.setId(id);
			// 비빌번호는 절대로 복호화 되지 않도록 암호화
			pDTO.setPassword(EncryptUtill.encHashSHA256(password));

			// 아이디 비밀번호 일치하는지 확인하기 위해 projectservice 호출
			pDTO = docuserservice.getUserLoginCheck(pDTO);

			if (pDTO != null) {
				session.setAttribute("SS_ID", id);
				session.setAttribute("SS_EMAIL", EncryptUtill.decAES128CBC(pDTO.getEmail()));
				session.setAttribute("SS_VERIFY", pDTO.getVerify());
				session.setAttribute("SS_USER_NAME", pDTO.getUser_name());
				session.setAttribute("SS_USER_NO", pDTO.getUser_no());

				res = 1;

			}
		} catch (Exception e) {

			log.info(e.toString());
			e.printStackTrace();
			res = 2;
		} finally {

			log.info("SS_id: " + session.getAttribute("SS_ID"));
			log.info("SS_VERIFY: " + session.getAttribute("SS_VERIFY"));
			log.info("SS_USER_NAME: " + session.getAttribute("SS_USER_NAME"));
			log.info("SS_USER_NO: " + session.getAttribute("SS_USER_NO"));
			if (pDTO != null) {
				model.addAttribute("url", "/project/user/index.do");
			} else {
				model.addAttribute("url", "/project/login.do");
			}
			log.info(this.getClass().getName() + ".getUserLoginCheck end!");

			model.addAttribute("res", String.valueOf(res));

			pDTO = null;
		}
		return "/project/LoginResult";
	}

	// 아이디찾기
	@RequestMapping(value = "project/searchId")
	public String searchId() {

		return "/project/searchId";
	}

	// 아이디 찾기 기능
	@RequestMapping(value = "project/searchIdProc")
	public String searchIdProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "searchIdProc Start!");

		String email = request.getParameter("email");
		String user_name = request.getParameter("user_name");
		
		log.info("email: " + email);
		log.info("user_name: " + user_name);

		DocUserDTO pDTO = new DocUserDTO();
		pDTO.setEmail(EncryptUtill.encAES128CBC(email));
		pDTO.setUser_name(user_name);

		pDTO = docuserservice.searchId(pDTO);
		// 결과값인 pDTO를 모델에 넣는다
		log.info("로그 테스트입니다.");

		if (pDTO != null) {
			model.addAttribute("msg", "아이디는"+pDTO.getId()+"입니다.");
			model.addAttribute("url", "/project/login.do");
			
		} else {
			model.addAttribute("msg", "아이디가 존재하지 않습니다.");
			model.addAttribute("url", "/project/login.do");

		}
		
		return "/project/Msg";
	}

	// 로그아웃
	@RequestMapping(value = "project/Logout")
	public String logout(ModelMap model, HttpSession session) {

		log.info(this.getClass().getName() + ".logout  start");

		log.info(this.getClass().getName() + ".logout  end");

		return "/project/LogoutResult";
	}

	// 회원가입
	@RequestMapping(value = "project/signup")
	public String signup() {
		return "/project/signup";
	}

	// 아이디 중복체크
	@ResponseBody
	@RequestMapping(value = "/project/checkId.do")
	public int idCheck(HttpServletRequest request, Model model) throws Exception {
		log.info(this.getClass().getName() + "checkId Start!");

		String id = request.getParameter("id");
		log.info("id:" + id);

		DocUserDTO pDTO = new DocUserDTO();

		pDTO.setId(id);

		return docuserservice.checkId(pDTO);

	}

	// 회원가입 값받기
	@RequestMapping(value = "project/insertUserinfo")
	public String insertUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + ".Project insertUserinfo start");

		// 회원가입 결과에 대한 메시지 전달 변수
		String msg = "";

		// 웹에서 받은 정보를 저장하는 변수
		DocUserDTO pDTO = null;

		try {
			/*
			 * 웹에서 받은 정보를 String 변수에 저장 시작 무조건 웹에서 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장
			 */
			String id = CmmUtil.nvl(request.getParameter("id"));
			String user_name = CmmUtil.nvl(request.getParameter("user_name"));
			String password = CmmUtil.nvl(request.getParameter("password"));
			String email = CmmUtil.nvl(request.getParameter("email"));

			/*
			 * 웹에서 받은 정보를 String 변수에 저장 끝 무조건웹에서 받은 정보는 DTO에 저장하기위해 임시로 String 변수에 저장
			 */

			/*
			 * 반드시 값을 받았으면 로그를 찍기
			 */

			log.info("id:" + id);
			log.info("user_name:" + user_name);
			log.info("password:" + password);
			log.info("email:" + email);

			/*
			 * 웹에서 받은 정보를 DTO에 저장하기 시작 웹에서 받은 정보는 무조건 DTO에 저장
			 */
			// 웹에서 받은 정버를 저장할 변수를 메모리에 올리기
			pDTO = new DocUserDTO();

			pDTO.setId(id);
			pDTO.setUser_name(user_name);
			pDTO.setPassword(EncryptUtill.encHashSHA256(password));
			pDTO.setEmail(EncryptUtill.encAES128CBC(email));

			/*
			 * 웹에서 받은 정보 DTO에 저장하기 끝 무조건 웹에서 받은 정보는 DTO에 저장
			 */

			/*
			 * 회원가입
			 */
			int res = docuserservice.InsertUserInfo(pDTO);

			log.info(res);
			if (res == 1) {
				msg = "회원가입 되었습니다.";
				model.addAttribute("url", "/project/login.do");
			} else if (res == 2) {
				msg = "이미 가입된 이메일 주소입니다.";
				model.addAttribute("url", "/project/login.do");
			} else {
				msg = "오류로 인해 회원가입이 실패하였습니다";
				model.addAttribute("url", "/project/login.do");
			}

		} catch (Exception e) {
			// 저장이 실패되면 사용자에게 보여줄 메세지
			msg = "실패하였습니다." + e.toString();
			log.info(e.toString());
			e.printStackTrace();
		} finally {
			log.info(this.getClass().getName() + ".insertuserinfo end!");

			// 회원가입 엽 결과 메세지 전달하기
			model.addAttribute("msg", msg);

			// 회원가입 여부 결과 메세지 전달하기
			model.addAttribute("pDTO", pDTO);

			// 변수 초기화
			pDTO = null;
		}

		return "/project/Msg";
	}

	// 마이페이지 수정
	@RequestMapping(value = "project/user/mypageUpdate")
	public String mypageUpdate(HttpSession session, Model model) throws Exception {
		log.info(this.getClass().getName() + "mypageUpdate start");

		String user_no = (String) session.getAttribute("SS_USER_NO");
		String id = (String) session.getAttribute("SS_ID");
		String email = (String) session.getAttribute("SS_EMAIL");
		String user_name = (String) session.getAttribute("SS_USER_NAME");

		log.info("session_user_no:"+user_no);
		log.info("session_id:"+id);
		log.info("session_email:"+email);
		log.info("session_user_name:"+user_name);

		return "project/user/mypageUpdate";
	}

	// 마이페이지 수정 기능
	@RequestMapping(value = "project/mypageUpdateProc")
	public String mypageUpdateProc(HttpServletRequest request, Model model, HttpSession session) throws Exception {

		log.info(this.getClass().getName() + "mypage start");

		String user_no = (String) session.getAttribute("SS_USER_NO");
		String id = (String) session.getAttribute("SS_ID");
		String user_name = request.getParameter("user_name");
		String password = CmmUtil.nvl(request.getParameter("password"));

		log.info("user_no:" + user_no);
		log.info("id:" + id);
		log.info("user_name:" + user_name);
		log.info("password:" + password);

		DocUserDTO pDTO = new DocUserDTO();

		pDTO.setUser_no(user_no);
		pDTO.setId(id);
		pDTO.setUser_name(user_name);
		pDTO.setPassword(EncryptUtill.encHashSHA256(password));

		int res = docuserservice.mypageUpdate(pDTO);

		if (res > 0) {
			model.addAttribute("url", "/project/user/mypageUpdate.do");
			model.addAttribute("msg", "수정 되었습니다.");

			session.setAttribute("SS_USER_NAME", user_name);
			
		} else {
			model.addAttribute("url", "/project/user/index.do");
			model.addAttribute("msg", "수정 실패 되었습니다.");
		}

		return "/project/Msg";
	}

	// 마이페이지 삭제
	@RequestMapping(value = "project/user/mypageDelete")
	public String mypageDelete(HttpSession session, Model model) throws Exception {

		log.info(this.getClass().getName() + ".mypageDelete start!");

		String user_no = (String) session.getAttribute("SS_USER_NO");
		String id = (String) session.getAttribute("SS_ID");
		log.info(user_no);
		log.info(id);

		DocUserDTO pDTO = new DocUserDTO();

		pDTO.setUser_no(user_no);
		pDTO.setId(id);

		try {

			int result = docuserservice.mypageDelete(pDTO);

			if (result > 0) {
				model.addAttribute("msg", "삭제되었습니다.");
				model.addAttribute("url", "/project/user/index.do");
				session.removeAttribute("SS_VERIFY");
			} else {
				model.addAttribute("msg", "삭제 실패되었습니다.");
				model.addAttribute("url", "/project/user/mypageUpdate.do");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			log.info(this.getClass().getName() + "mypageDelete end!");
			pDTO = null;
		}

		return "/project/Msg";
	}

	// 비밀번호 찾기
	@RequestMapping(value = "project/searchPwd")
	public String searchPwd() {

		return "/project/searchPwd";
	}

	// 비밀번호 찾기 기능
	@RequestMapping(value = "project/searchPwdProc")
	public String searchPwdProc(HttpServletRequest request, Model model) throws Exception {
		log.info(this.getClass().getName() + ".searchPwd  start");

		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String password = temporaryPassword(8);
		log.info("id: " + id);
		log.info("email: " + email);
		log.info("password: " + password);

		DocUserDTO pDTO = new DocUserDTO();
		pDTO.setId(id);
		pDTO.setEmail(EncryptUtill.encAES128CBC(email));
		pDTO.setPassword(EncryptUtill.encHashSHA256(password));

		int result = 0;

		try {

			result = docuserservice.searchPwd(pDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("여기까지");

		if (result > 0) {

			String title = "변경된 비밀번호 입니다.";
			String contents = "변경된 비밀번호는" + password + "입니다.";

			log.info("메일 제목Title:" + title);
			log.info("메일 내용 contents:" + contents);

			MailDTO mDTO = new MailDTO();

			mDTO.setTitle(title);
			mDTO.setContents(contents);
			mDTO.setToMail(email);

			int res = 0;

			res = mailService.doSendMail(mDTO);

			if (res == 1) {
				log.info("mail.sendMail success!!!");
			} else {
				log.info("mail.sendMail fail!!!");
			}

			model.addAttribute("url", "/project/login.do");
			model.addAttribute("msg", "비밀번호가 메일로 발송되었습니다");
			return "/project/Msg";

		} else {
			model.addAttribute("url", "/project/login.do");
			model.addAttribute("msg", "비밀번호 찾기에 실패하였습니다.");

			return "/project/Msg";

		}

	}

	// 회원 리스트
	@RequestMapping(value = "project/user/UserList")
	public String getUserList(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam(defaultValue = "1") int curPage) throws Exception {
		log.info("getUserList Start!!");

		String id = (String) session.getAttribute("SS_ID");

		int listCnt = docuserservice.AllUserData();
		log.info("listCnt:  " + listCnt);
		log.info("curPage:  " + curPage);
		PagingDTO pagination = new PagingDTO(listCnt, curPage);

		int startIndex = pagination.getStartIndex();
		int endIndex = pagination.getEndIndex();

		DocUserDTO pDTO = new DocUserDTO();
		pDTO.setStartIndex(startIndex);
		pDTO.setEndIndex(endIndex);
		pDTO.setId(id);

		List<DocUserDTO> rList = docuserservice.getUserList(pDTO);

		if (rList == null) {
			log.info("실패");
			rList = new ArrayList<DocUserDTO>();
		}

		model.addAttribute("rList", rList);
		model.addAttribute("listCnt", listCnt);
		model.addAttribute("pagination", pagination);
		rList = null;

		log.info(this.getClass().getName() + ".getUserList end!");

		return "/project/user/UserList";
	}

	// 회원 삭제 관리자
	@RequestMapping(value = "project/user/UserDelete.do")
	public String UserDelete(HttpSession session, Model model, ServletRequest request) throws Exception {

		log.info(this.getClass().getName() + ".UserDelete start!");

		String user_no = request.getParameter("user_no");

		log.info("user_no: " + user_no);

		DocUserDTO pDTO = new DocUserDTO();
		pDTO.setUser_no(user_no);

		try {

			int result = docuserservice.userDelete(pDTO);

			if (result > 0) {
				model.addAttribute("msg", "삭제되었습니다.");
				model.addAttribute("url", "/project/user/UserList.do");
			} else {
				model.addAttribute("msg", "삭제 실패되었습니다.");
				model.addAttribute("url", "/project/user/UserList.do");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			log.info(this.getClass().getName() + "UserDelete end!");
			pDTO = null;
		}

		return "/project/Msg";
	}

	// 검색 기능
	@RequestMapping(value = "project/UserTest")
	public String UserTest(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			HttpSession session, @RequestParam(defaultValue = "1") int curPage) throws Exception {

		log.info(this.getClass().getName() + "UserTest start");

		String id = (String) session.getAttribute("SS_ID");
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");

		log.info("sDTOsearchType:  " + searchType);
		log.info("sDTOkeyword:  " + keyword);

		SearchDTO sDTO = new SearchDTO();
		sDTO.setSearchType(searchType);
		sDTO.setKeyword(keyword);

		int listCnt = docuserservice.AllUserDataSearch(sDTO);

		log.info("listCnt:  " + listCnt);
		log.info("curPage:  " + curPage);

		PagingDTO pagination = new PagingDTO(listCnt, curPage);

		int startIndex = pagination.getStartIndex();
		int endIndex = pagination.getEndIndex();

		log.info("startIndex:  " + startIndex);
		log.info("endIndex:  " + endIndex);

		log.info("bDTOsearchType:  " + searchType);
		log.info("bDTOkeyword:  " + keyword);

		DocUserDTO pDTO = new DocUserDTO();
		pDTO.setStartIndex(startIndex);
		pDTO.setEndIndex(endIndex);

		pDTO.setSearchType(searchType);
		pDTO.setKeyword(keyword);
		pDTO.setId(id);

		List<DocUserDTO> rList = docuserservice.getUserListSearch(pDTO);

		if (rList == null) {
			rList = new ArrayList<DocUserDTO>();
		}
		// 죄회된 결과값 넣어줌
		model.addAttribute("rList", rList);
		model.addAttribute("listCnt", listCnt);
		model.addAttribute("pagination", pagination);

		// 변수 초기화
		rList = null;

		log.info(this.getClass().getName() + ".UserTest end!");

		return "project/user/UserList";

	}

	// 이메일 중복 체크
	@ResponseBody
	@RequestMapping(value = "project/checkEmail.do")
	public int emailcheck(HttpServletRequest request) throws Exception {

		log.info("checkEmail start");
		
		String email = request.getParameter("email");
		
		log.info("email:  "+ email);
		
		
		int result = docuserservice.checkEmail(EncryptUtill.encAES128CBC(email));
		
		
		return result;

	}

	// ----------------------------------------------------------------------------함수----------------------------------------------------------------------------//

	// mailDTO선언
	// dosend메일 사용

	// 비밀번호 랜덤 생성
	public static String temporaryPassword(int size) {

		StringBuffer buffer = new StringBuffer();

		Random random = new Random();

		String chars[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9"
				.split(",");

		for (int i = 0; i < size; i++) {

			buffer.append(chars[random.nextInt(chars.length)]);

		}

		return buffer.toString();

	}

}
