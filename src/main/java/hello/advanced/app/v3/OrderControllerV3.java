package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orderServiceV3;
    private final LogTrace trace;   //Bean에 등록되어있는 해당 인터페이스를 상속받은 생성자로 호출함

    //생성자 필드에 값이 들어가서 파라미터를 전달하지 않고 사용. 파라미터를 넘겨서 사용시 변경할 때 매우 복잡(-> 파라미터를 넘기는 모든 부분을 수정해야해서)
    @GetMapping("/v3/request")
    public String request(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");
            orderServiceV3.orderItem(itemId);
            trace.end(status);
            return "ok";
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; //예외를 꼭 다시 던져주어야 한다. 만약 던지지 않는다면 예외가 아닌 정상적인 흐름이라고 생각하고 넘어간다.
        }
    }
}
