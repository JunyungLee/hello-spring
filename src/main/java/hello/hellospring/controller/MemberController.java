package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //이런식으로 하는게 스프린 빈을 등록하는 방법 중 1. 컴포넌트 스캔 방식
public class MemberController {

    //@Autowired //DI 종류 -> 2) 필드주입
    private MemberService memberService;

/*    @Autowired //DI 종류 -> 3) setter 주입  -> 개발자 누구든지 호출할 수 있는 주의점이 있음
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }*/

   @Autowired // DI 종류 -> 1) 셍성자를 통해서 -> 스프링 빈 등록 : 생성자 주입 //가장 권장하는 방법
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
       return "members/createMemberForm";
    }

    @PostMapping("/members/new") //데이터를 넘길때 post 사용 -> form 데이터 post 방식으로 전달함
    public String create(MemberForm form) {
       Member member = new Member();
       member.setName(form.getName());

       memberService.join(member);

       return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
