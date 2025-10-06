package com.dawull.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @SrpingBootApplication 은 자동설정, 스프링 bean 읽기,생성 모두 자동 설정.
 * 해당 어노테이션이 있는 위치부터 설정을 읽어가기 떄문에
 * 항상 프로젝트 최상단에 위치.
 */
@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        /**
         *  .run 을 통해 내장was(web application server)를 실행.
         *  내장was가 존재하여 톰캣을 설치할 필요가 없음.
         *  스프링부트로 만들어진 jar 파일(실행 가능한 java 패키징 파일)로 실행하면 됨.
         *
         *  스플이부트에서 내장was를 사용하기를 권장한다. 왜?
         *  언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있기 때문.
         *  외장was를 쓴다하면 이것저것 버전,설정을 일치시켜야만한다. 즉, 비효율!
         *
         * */
    }
}
