package hello.core.Singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class singletonTest {

    //기존에는 요청할 때 마다 객체를 새로 생성하였는데, 객체가 1개만 생성되고 공유하도록 설계해야한다 -> 싱글톤 패턴
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberSerivce2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){ //static메서드: 객체생성없이 '클래스이름.메서드이름()'으로 호출
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        //same == 대상의 주소(reference)를 비교
        //equal 객체간의 값(value)를 비교
    }

    /*
        싱글톤 컨테이너 적용 후 : 스프링 컨테이너 덕분에 고객이 요청이 올 때마다 객체를 생성하는 것이 아니라,
        이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있도록 재사용하는것
     */

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){

        //AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("mmm", MemberService.class);

        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("mmm", MemberService.class);

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 == memberSerivce2
        assertThat(memberService1).isSameAs(memberService2);
    }
}






















