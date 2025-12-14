package gj.avengers.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
/*
흐름 정리
헐크가 분노 게이지가 5가 넘으면 헐크체인지이벤트 시작,
헐크로 변신하면 스케쥴로 매1초 마다 헐크버스터 공격
헐크가 기절하면 헐크체인지이벤트 종료.
헐크버스터가 전투불능되도 헐크체인지이벤트 종료.
헐크 공격 당하면 리액션으로 간지러움, 아픔, 멈춤, 기절 등 상태 보내줘야
헐크버스터가
 */