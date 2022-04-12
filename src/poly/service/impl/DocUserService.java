package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.DocUserDTO;
import poly.dto.SearchDTO;
import poly.persistance.mapper.DocUserMapper;
import poly.service.IDocUserService;
import poly.util.CmmUtil;


@Service("DocUserService")
public class DocUserService implements IDocUserService {

	
	@Resource(name="DocUserMapper")
	private DocUserMapper docusermapper;
	
	@Override
	public int InsertUserInfo(DocUserDTO pDTO) throws Exception {
		
		// 회원가입 성공 :1, 아이디 중복으로 인한 가입취소:2, 기타에러:0
		int res =0;
		
		//controller에서 값이 정상적으로 못넘어오는 경우 대비
		if(pDTO ==null) {
			pDTO = new DocUserDTO();
		}
		
		//회원가입 중복 방지를 위해 DB에서 데이터 조회
		/*
		 * DocUserDTO rDTO = docusermapper.getUserExists(pDTO);
		 * 
		 * //mapper에서 값이 정상적으로 못넘어오는 경우대비 if(rDTO == null) { rDTO = new DocUserDTO(); }
		 */
		
		/*//중복된 회원정보가 있는 경우 , 2반환 더이상 진행 하지 않음
		if(CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
			res=2;
		}*/
		//회원가입 중복이 아니므로 회원가입 진행
		else {
			//회원가입
			int success = docusermapper.InsertUserInfo(pDTO);
			
			if(success >0) {
				res =1;
			}else {
				res =0;
			}
			
		}
		
		return res;
	}
	
	//로그인체크
	@Override
	public DocUserDTO getUserLoginCheck(DocUserDTO pDTO) throws Exception{
		
		return docusermapper.getUserLoginCheck(pDTO);
	}

	//아이디 중복확인
	@Override
	public int checkId(DocUserDTO pDTO) throws Exception {
		
		return docusermapper.checkId(pDTO);
	}
		
	//마이페이지 수정
	@Override
	public int mypageUpdate(DocUserDTO pDTO) throws Exception {
		
		return docusermapper.mypageUpdate(pDTO);
	}
	
	
	//마이페이지 삭제
	@Override
	public int mypageDelete(DocUserDTO pDTO) throws Exception {
		
		return docusermapper.mypageDelete(pDTO);
	}
	
	//아이디 찾기
	@Override
	public DocUserDTO searchId(DocUserDTO pDTO) throws Exception {
		
		return docusermapper.searchId(pDTO);
	}

	
	//비밀번호 찾기
	@Override
	public int searchPwd(DocUserDTO pDTO) throws Exception {
		// TODO Auto-generated method stub
		return docusermapper.searchPwd(pDTO);
	}

	
	//회원 리스트
	@Override
	public List<DocUserDTO> getUserList(DocUserDTO pDTO) throws Exception {
		
		return docusermapper.getUserList(pDTO);
	}
	
	
	//회원 삭제 관리자
	@Override
	public int userDelete(DocUserDTO pDTO) throws Exception {
		
		return docusermapper.userDelete(pDTO);
	}
	
	//회원 리스트 전체 개수
	@Override
	public int AllUserData() throws Exception {

		return docusermapper.AllUserData();
	}
	
	//리스트 검색
	@Override
	public List<DocUserDTO> getUserListSearch(DocUserDTO pDTO) throws Exception {

		return docusermapper.getUserListSearch(pDTO);
	}
	
	//리스트 검색 개수
	@Override
	public int AllUserDataSearch(SearchDTO sDTO) throws Exception {

		return docusermapper.AllUserDataSearch(sDTO);
	}
	
	//이메일 중복 체크
	@Override
	public int checkEmail(String email) throws Exception {
		
		return docusermapper.checkEmail(email);
	}
	
	

	
	
	
	

}
