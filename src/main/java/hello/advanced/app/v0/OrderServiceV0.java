package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

    private final OrderRepositoryV0 orderRepositoryVO;

    // 비즈니스 로직
    public void orderItem(String itemId) {
        orderRepositoryVO.save(itemId);
    }
}
