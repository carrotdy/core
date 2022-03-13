package hello.core.Singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

/*
    Test 클래스가 outer 클래스이고, TestConfig 클래스가 inner 클래스
    Test 클래스 내 TestConfig 클래스에 static 키워드를 뺀다면 Test 클래스가 생성되어야 TestConfig를 사용할 수 있음 
    그러나 Test 클래스 내에서는 이미 TestConfig가 생성되기도 전에 스프링 컨테이너에서 TestConfig 빈이 있는지 찾아오려고 함 -> 스프링에서는 그런 빈이 없다고 나옴
 */

//무상태로 설계해야 한다!!! -> 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 함

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10,000원 주문
        int userAPrice = statefulService1.order("userA", 10000);

        //ThreadA : B사용자 20,000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //ThreadA : 사용자A가 주문금액을 조회함
        //int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

        //Assertions.assertThat(statefulService1.userAPrice()).isEqualTo(10000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}