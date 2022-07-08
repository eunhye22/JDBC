package org.zerock.myapp;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


//========================================//
// 1. JUnit5 -> "JUnit Jupyter"로 이름이 바뀜
// 2. 사용가능한 어노테이션이 많이 증가
// 3. 테스트 클래스로부터, 테스트 객체를 생성하는 방법이 2가지로 바뀜:
//    (1) PER_CLASS (테스트 객체 1개만 생성)
//	  (2) PER_METHOD (테스트 메소드 수행시마다, 객체 생성)
// 4. 테스트 메소드의 수행 순서를 결정할 수 있게됨 (@TestMethodOrder)
//========================================//

@Log4j2
@NoArgsConstructor
// 타입선언부 위에, 아래와 같이 기본적인 2개의 어노테이션을 설정

// 테스트 인스턴스의 생성기준을 설정
@TestInstance(Lifecycle.PER_CLASS)    //또는 @TestInstance(Lifecycle.PER_METHOD)

// 테스트 클래스 안에 여러개의 테스트 메소드가 있을 때,
// 테스트 클래스의 전체 테스트 메소드 수행시 
// 각 메소드의 수행순서를 @Order 어노테이션을 통해 실행순서를 지정할 때 사용함
@TestMethodOrder(OrderAnnotation.class)

public class JUnit5_Template {
	
	// 사전처리 메소드 (@BeforeAll, @BeforeEach)는 선택이다!!!!
	@BeforeAll
	void beforeAll() {
		log.trace("beforeAll() invoked.");
	}
	@BeforeEach
	void beforeEach() {
		log.trace("beforeEach() invoked.");
	}
	
	@Test
	@Order(2)		// 테스트 메소드의 실행순서 지정 
	@DisplayName("덧셈 메소드의 기능을 테스트 합니다.")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void contextLoad1() {
		log.trace("contextLoad1() invoked.");
	}
	
	@Test
	@Order(1)
	@DisplayName("곱셈 메소드의 기능을 테스트 합니다.")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void contextLoad2() {
		log.trace("contextLoad2() invoked.");
	}
	
	// 사후처리 메소드 (@AfterAll, @AfterEach)는 선택이다!!!!
	@AfterEach
	void afterEach() {
		log.trace("afterEach() invoked.");
	}
	
	@AfterAll
	void afterAll() {
		log.trace("afterAll() invoked.");
	}	
	
//	void testContextLoads() {
//		log.trace("testContextLoads() invoked.");
//		
//	} // beforeEach

} //class
