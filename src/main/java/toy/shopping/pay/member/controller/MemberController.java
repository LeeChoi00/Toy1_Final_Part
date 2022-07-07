package toy.shopping.pay.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import toy.shopping.pay.member.model.exception.MemberException;
import toy.shopping.pay.member.model.service.MemberService;
import toy.shopping.pay.member.model.vo.Member;

@SessionAttributes("loginUser")
@Controller
public class MemberController {
	@Autowired
	private MemberService mService;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@RequestMapping(value="login.me", method=RequestMethod.POST)
	private String login(Member m, Model model) {
		System.out.println(bcrypt.encode(m.getUserPwd()));
		Member loginMember = mService.login(m);
		
		boolean match = bcrypt.matches(m.getUserPwd(), loginMember.getUserPwd());
		
		if(match) {
			model.addAttribute("loginUser", loginMember);
			return "redirect:home.do";			
		} else {
			throw new MemberException("로그인에 실패했습니다.");
		}
	}
	
	@RequestMapping("logout.me")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:home.do";
	}
}
