package hello.core;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //설정(구성)정보로 사용
public class AppConfig {

    //@Bean memberService -> new new MemoryMemberRepository();
    //@Bean orderService  -> new new MemoryMemberRepository();

    /*
    각각 다른 2개의 MemoryMemberRepository가 생성되면서 싱글톤이 깨지는것처럼 보이지만 아님!
    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.memberRepository
    call AppConfig.orderService
    call AppConfig.memberRepository 이렇게 호출되어야되는게 맞는데

    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.discountPolicy 이렇게 호출이 된다.
    
    call AppConfig.memberRepository 세번 호출이 되어야하는데 중복된건 한번만 호출이 됨 -> 싱글톤 보장 ->
    @Configuration 때문에 한번만 호출됨 -> 없으면 모두 호출되고 싱글톤 보장안됨
     */

    @Bean(name="mmm")  //이렇게 name을 사용해도 되지만 기본을 따라가는게 좋음!!!!!!!
    //@Bean 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}













