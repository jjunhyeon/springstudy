package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private OrderRepositoryV5 orderRepository;
    private TraceTemplate traceTemplate;

    private OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace logTrace){
        this.orderRepository = orderRepository;
        this.traceTemplate = new TraceTemplate(logTrace);
    }

    // 비즈니르 로직
    public void orderItem(String itemId){
        traceTemplate.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
