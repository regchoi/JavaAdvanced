package hello.proxy.app.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

 /**
 * 스프링은 @Controller 또는 @RequestMapping이 있어야 스프링 컨트롤러로 인식, **SpringBoot 3.0 이상부터는 Controller로 해야해
 * -> isHandler 메서드에서 더이상 RequestMapping을 핸들러로 인식하지 않기 때문
 */
@Controller
@ResponseBody

public interface OrderControllerV1 {

    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();
}
