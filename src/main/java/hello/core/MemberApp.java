package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();  //memberService에는 MemberServiceImpl이 들어가있음
        //MemberService memberService = new MemberServiceImpl();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);  //Appconfig 환경설정을 가지고 관리해줌
        MemberService memberService = ac.getBean("mmm", MemberService.class); //객체이름/타입

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
        System.out.println("확인 : " + member.equals(findMember));

    }
}
