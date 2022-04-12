package poly.service;

import java.util.List;

import poly.dto.DocUserDTO;
import poly.dto.SearchDTO;

public interface IDocUserService {
	
	//회원등록하기
	int InsertUserInfo(DocUserDTO pDTO) throws Exception;
	
	//로그인을 위해 아이디 비밀번호 확인
	DocUserDTO getUserLoginCheck(DocUserDTO pDTO) throws Exception;
	
	//아이디 중복 체크
	int checkId(DocUserDTO pDTO)throws Exception;
	
	//아이디 찾기
	DocUserDTO searchId(DocUserDTO pDTO) throws Exception;
	
	//마이페이지 수정
	int mypageUpdate(DocUserDTO pDTO) throws Exception;
	
	//마이페이지 삭제
	int mypageDelete(DocUserDTO pDTO)throws Exception;

	
	//비밀번호 찾기
	int searchPwd(DocUserDTO pDTO)throws Exception;
	
	//회원 리스트
	List<DocUserDTO> getUserList(DocUserDTO pDTO) throws Exception;
	
	//회원 삭제 관리자
	int userDelete(DocUserDTO pDTO) throws Exception;
	
	//회원 리스트 전체 개수
	int AllUserData() throws Exception;
	
	//리스트 검색
	List<DocUserDTO> getUserListSearch(DocUserDTO pDTO) throws Exception;
	
	//리스트 검색 개수
	int AllUserDataSearch(SearchDTO sDTO) throws Exception;
	
	//이메일 중복 체크
	int checkEmail(String email) throws Exception;

	
		
	
	

	
	
}
