package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; /*viewResolver가 읽어서 templates 밑에 일치하는것 찾음*/
    }

    //MVC 사용하기
    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
        /*http://localhost:8080/hello-mvc?name=spring!!!! => 실행시킬 때 parameter에 name 명시 해주어야 한다 */
    }

    // API 방식으로 사용하기
    @GetMapping("hello-string")
    @ResponseBody /*body 부에 직접 넣어주겠다*/
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //"hello spring" : view 없이 그대로 전달
    }

    //실질적으로 API 방식을 사용하는 이유 -> JSON 방식으로 데이터가 나옴
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi (@RequestParam("name") String name) {
        Hello hello = new Hello();/*객체 만들기*/
        hello.setName(name);
        return hello; /*객체 리턴*/
    }
    //객체라면 viewResolver 대신에 HttpMessageConverter가 동작한다.
    // 스프링 컨테이너에서 기본 객체라면 JsonConverter(MappingJackson2HttpMessageConverter)가 동작해서 JSON을 전달한다
    // 스프링 컨테이너에서 기본 문자라면 StringConverter(StringHttpMessageConverter) 동작한다.

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        /*getter setter 만들기 단축키 : control + Enter*/

    }
}