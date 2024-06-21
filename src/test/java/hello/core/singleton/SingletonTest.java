package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("no spring di container")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1. 조회: 호출마다 객체 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("singleton pattern")
    void singletonServiceTest(){
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        Assertions.assertThat(instance1).isSameAs(instance2);
        //same ==
        //equal string 이 같다.

    }

    @Test
    @DisplayName("spring container and singleton")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회: 호출마다 객체 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        //2. 조회: 호출마다 객체 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
