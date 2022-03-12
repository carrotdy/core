package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {
    //다양한 설정 형식이 지원 - 여기에서는 자바코드로 설정 사용 , ApplicationContext은 getBeanDefinition을 사용하지못함
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    //다양한 설정 형식이 지원 - 여기에서는 xml 설정 사용
    //GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName); //Bean에 대한 meta data 정보들을 반환, 코드에서는 스프링이 내부에서 사용하는 빈을 getRole() 로 구분하기 위해 사용함.

            //ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
            //ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName + "beanDefinition = " + beanDefinition);
            }
        }
    }
}
