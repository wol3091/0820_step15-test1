package score.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import score.bean.ScoreDTO;
import score.dao.ScoreDAO;
@Controller
public class ScoreDeleteController{

	@RequestMapping(value="/score/scoreDelete.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("성적 삭제 처리");
		
		// 1. 사용자 입력 정보 추출
		String studNo = request.getParameter("studNo");
		int pg = Integer.parseInt(request.getParameter("pg"));
		// 2. DB 연동 처리			
		ScoreDAO scoreDAO = new ScoreDAO();
		ScoreDTO scoreDTO = new ScoreDTO();
		scoreDTO.setStudNo(studNo);
		int su = scoreDAO.deleteScore(scoreDTO);
		// 3. 화면 네비게이션	
//		request.setAttribute("pg", pg);
//		request.setAttribute("su", su);		
//		return "scoreDelete";	// "scoreDelete.jsp"
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pg", pg);
		modelAndView.addObject("su", su);
		modelAndView.setViewName("scoreDelete.jsp");
		return modelAndView;
	}

}







