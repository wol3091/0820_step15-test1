package score.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import score.bean.ScoreDTO;
import score.dao.ScoreDAO;
@Controller
public class ScoreWriteController{
	@RequestMapping(value="/score/scoreWrite.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("성적 등록 처리");
		
		// 데이터
		// 1. 사용자 입력 정보 추출
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int insertCount = 0;
		ScoreDTO scoreDTO = new ScoreDTO();
		scoreDTO.setStudNo(request.getParameter("studNo"));
		scoreDTO.setName(request.getParameter("name"));
		
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int mat = Integer.parseInt(request.getParameter("mat"));
		int tot = kor + eng + mat;
		double avg = (double)tot / 3;
		
		scoreDTO.setKor(kor);
		scoreDTO.setEng(eng);
		scoreDTO.setMat(mat);
		scoreDTO.setTot(tot);
		scoreDTO.setAvg(avg);
		// 2. DB 연동 처리
		ScoreDAO scoreDAO = new ScoreDAO();
		insertCount = scoreDAO.insertScore(scoreDTO);
		// 3. 화면 네비게이션
//		request.setAttribute("insertCount", insertCount);		
//		return "scoreWrite";	// "scoreWrite.jsp"
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("insertCount", insertCount);
		modelAndView.setViewName("scoreWrite.jsp");
		return modelAndView;
	}

}









