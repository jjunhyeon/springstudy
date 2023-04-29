package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
/*
 *  로그 추적기 개발
 *  요구사항 만족 하지만
 *  남은 문제 :
 *  호출하는 파라미터에 TraceId의 동기화를 위해서 관련 메서드의 모든 파라미터를 수정해야한다.
 *  다른 대안 필요.
 *
 * */
public class OrderControllerV2 {

    private final OrderServiceV2 orderServiceV2;
    private final HelloTraceV2 trace;

    @GetMapping("/v2/request")
    public String request(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderServiceV2.orderItem(status.getTraceId(),itemId);
            trace.end(status);
            return "ok";
        } catch(Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
