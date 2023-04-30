package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/*
 * 전략패턴
 * 실행할 때마다 전략을 유연하게 변경할 수 있다.
 * */
@Slf4j
public class ContextV2Test {

    /*
     * 전략 패턴 사용
     * 파라미터로 처리하는 방식에 대한 테스트 코드
     * 선 조립 후 실행 방식이 아닌
     * 실행할 때마다 전략을 인수로 전달한다.
     * */
    @Test
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    /*
    * 전략패턴 익명 내부 클래스
    * */
    @Test
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        context.execute(new Strategy(){
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    /*
     * 전략패턴 람다
     * */
    @Test
    void strategyV3() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직1 실행"));
        context.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
