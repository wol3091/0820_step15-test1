package score.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ScoreWriteFormController{

	@RequestMapping(value="/score/scoreWriteForm.do")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//return "scoreWriteForm";	// ./scoreWriteForm.jsp
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("scoreWriteForm.jsp");
		return modelAndView;
	}

}








