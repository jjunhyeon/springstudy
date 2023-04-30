package hello.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

/*
* Context는 Strategy의 인터페이스만 의존하고 있다.
* 덕분에 Strategy의 구현체를 변경하거나 새로 만들어도 Context 코드에는 영향을 주지 않는다.'
* 스프링에서 주로 사용하는 방식
* */
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy){
        this.strategy = strategy;
    }

    public void execute(){
        long startTime = System.currentTimeMillis();
        //비즈니르 로직 실행
        strategy.call(); // 위임
        // 비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
