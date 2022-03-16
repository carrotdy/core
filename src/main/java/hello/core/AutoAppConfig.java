package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration  //설정(구성)정보로 사용
@ComponentScan( //자동으로 스프링 빈을 등록해줌 (소스코드를 열면 @Component 애노테이션이 붙어있다)
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
 /*
 컴포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록되기 때문에,
 AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고, 실행되어 버린다.
 그래서 excludeFilters 를 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외
 */

//자동 빈 보다 수동 빈 등록이 우선권을 가진다.
public class AutoAppConfig {
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
