package score.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import helper.RegexHelper;
import score.bean.ScoreDTO;
import score.dao.ScoreDAO;
@Controller
public class ScoreViewController{

	@RequestMapping(value="/score/scoreView.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("성적 상세보기 처리");
		
		// 1. 검색할 게시글 번호 추출
		String studNo = request.getParameter("studNo");
		String str_pg = request.getParameter("pg");
		int pg = 1;
		if(RegexHelper.getInstance().isNum(str_pg)) {
			pg = Integer.parseInt(str_pg);
		}	
		// 2. DB 연동 처리	
		ScoreDAO scoreDAO = new ScoreDAO();
		ScoreDTO scoreDTO = new ScoreDTO();
		scoreDTO.setStudNo(studNo);
		scoreDTO = scoreDAO.getScore(scoreDTO);
		// 3. 검색 결과를 request에 저장하고 상세 화면으로 이동한다.	
//		request.setAttribute("scoreDTO", scoreDTO);
//		request.setAttribute("studNo", studNo);
//		request.setAttribute("pg", pg);		
//		return "scoreView";		// "scoreView.jsp"
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("scoreDTO", scoreDTO);
		modelAndView.addObject("studNo", studNo);
		modelAndView.addObject("pg", pg);
		modelAndView.setViewName("scoreView.jsp");
		return modelAndView;
	}

}








