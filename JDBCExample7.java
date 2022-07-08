package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

// 스포: log 찍어보면 REsource3 -> 2 -> 1 순서대로 자원객체 해제됨

public class JDBCExample7 {
	
	// 우리가 직접 자원객체를 만들고, try-with-resources 블록에 적용해서
	// 과연 자원객체들이 어떤식으로 차례대로 닫히는지 알아보자
	
	// 실행블럭 --> 일단 밑에 class부터 생성하고 나중에 작성하는게 이해하기 편함
	public static void main(String args[]) {
		Resource1 res1 = new Resource1();
		Resource2 res2 = new Resource2();
		Resource3 res3 = new Resource3();
		
		try ( res1; res2; res3) {
			;;		// pass (아무것도 하지 않는다)
			
		} catch(Exception e) {
			e.printStackTrace();
		} // try-with-resources
	
	} //main

} // end class

@Log4j2
@NoArgsConstructor
class Resource1 implements AutoCloseable { /* 매개변수와 리턴타입이 없는 추상메소드가 하나 들어있음 --> functional interface */
											//	그 추상메소드를 오버라이딩 하자
											// 이유 : try-with-resources가 닫아줄 모든 자원객체(클래스)의 요건 --> AutoCloseable 해야하기때문
											// 31행 implements AutoCloseable { 이랑, 밑에 Override 주석처리 하고 올려보면 
											// res1 부분에 컴파일 에러 --> 메세지 읽어보면 왜 이렇게 해야하는지 이해가능!
	@Override
	public void close() throws Exception {
		log.trace("close() invoked.");
		
	} // close
	
} // end class     --> 복붙해서 자원클래스 3개로 만들기. 클래스이름만 변경

@Log4j2
@NoArgsConstructor
class Resource2 implements AutoCloseable { 
											
	@Override
	public void close() throws Exception {
		log.trace("close() invoked.");
		
	} // close
	
} // end class

@Log4j2
@NoArgsConstructor
class Resource3 implements AutoCloseable { 
											
	@Override
	public void close() throws Exception {
		log.trace("close() invoked.");
		
	} // close
	
} // end class
