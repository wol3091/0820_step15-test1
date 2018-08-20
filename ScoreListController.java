package score.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import helper.RegexHelper;
import score.bean.ScoreDTO;
import score.dao.ScoreDAO;
@Controller
public class ScoreListController{

	@RequestMapping(value="/score/scoreList.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("성적 목록 처리");
		// 1. 사용자 입력 정보 추출
		ArrayList<ScoreDTO> list = null;
		int pg = 1;
		int limit = 5;
		String str = request.getParameter("pg");
		// 문자열을 숫자로 변환할 때
		// 1. null 확인  2. 숫자만으로 구성되었는지
		if(RegexHelper.getInstance().isNum(str)) {		
			pg = Integer.parseInt(str);		
		}
		int endNum = pg * limit;
		int startNum = endNum - (limit -1);
		// 2. DB 연동 처리	
		ScoreDAO scoreDAO = new ScoreDAO();
		list = (ArrayList<ScoreDTO>)scoreDAO.getScoreList(startNum, endNum);
		// 페이징 처리
		int listCount = scoreDAO.selectListCount();
		int maxPage = (listCount + limit - 1) / limit;	// (26 + 5 - 1)/5 = 6 page
		int startPage = (pg-1)/3*3+1;
		int endPage = startPage + 2;
		if(endPage > maxPage) endPage = maxPage;
		// 3. 검색 결과를 request에 저장하고 목록 화면으로 이동한다.
//		request.setAttribute("list", list);
//		request.setAttribute("startPage", startPage);
//		request.setAttribute("endPage", endPage);
//		request.setAttribute("maxPage", maxPage);
//		request.setAttribute("pg", pg);		
//		return "scoreList";	// "scoreList.jsp"
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", list);
		modelAndView.addObject("startPage", startPage);
		modelAndView.addObject("endPage", endPage);
		modelAndView.addObject("maxPage", maxPage);
		modelAndView.addObject("pg", pg);
		modelAndView.setViewName("scoreList.jsp");
		return modelAndView;
	}

}







