package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *  로그 추적기 v4 버전
 *  템플릿 메서드 적용 버전
 * */
@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final TraceTemplate traceTemplate;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace logTrace){
        this.orderService = orderService;
        // trace의 의존관계 주입을 받는며 템플릿을 생성한다.
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
        traceTemplate.execute("OrderController.request()", () -> {
            orderService.orderItem(itemId);
            return null;
        });
        return "ok";
    }
}
