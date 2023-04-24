package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
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
 *  //-> V3 버전
 *  LogTraceConfig를 Configuration으로 등록해 파라미터 없이 처리하는걸 가능하게 해결했다.
 *  하지만 동시에 여러 Request를 날릴 시 동시성 문제를 겪고 있다.
 *
 * */
public class OrderControllerV3 {

    private final OrderServiceV3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId){
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderService.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch(Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
