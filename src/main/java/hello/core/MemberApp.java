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

        //ApplicationContext는 AnnotationConfigApplicationContext 상위 인터페이스
        //ApplicationContext는 기능이 적고, AnnotationConfigApplicationContext는 너무 많은 기능을 가지고 있음
        //개발을 할 때는 가급적 기능을 적게 제공하는 상위 인터페이스를 사용해야, 향후 구현 클래스가 변경되어도 클라이언트 코드를 변경하지 않아도 됨. 실제 스프링 애플리케이션을 개발할 때는 ApplicationContext를 사용
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);  //Appconfig 환경설정을 가지고 관리해줌
        MemberService memberService = ac.getBean("mmm", MemberService.class); //기본적으로 메서드 객체이름/타입

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
        System.out.println("확인 : " + member.equals(findMember));

    }
}
