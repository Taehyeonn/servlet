package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members") // 클래스 레벨 공통 경로 설정: 메서드 레벨의 중복 경로 제거 효과
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

//    @RequestMapping(value = "/new-form", method = RequestMethod.GET) //GET 요청의 경우에만 호출된다. //단순조회는 GET
    @GetMapping("/new-form") //위의 코드와 같은 역할.
    public String newForm() {
        return "new-form";
    }

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username, //1. 요청 파라미터 받고
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(username, age);      //2. 비즈니스 로직
        memberRepository.save(member);

        model.addAttribute("member", member); //3. 모델에 추가 후
        return "save-result";                               //4. 뷰의 논리 이름 반환
    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll(); //

        model.addAttribute("members", members);
        return "members";
    }
}
