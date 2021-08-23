package com.example.FastCampusWeb06.controller;

import com.example.FastCampusWeb06.dto.UserReq;
import com.example.FastCampusWeb06.dto.UserRes;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Api(tags = {"API 정보를 제공하는 controller"})//Controller을 제목을 바꿔준다.
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        //swaggerfox를 사용하면 자동으로 컨트롤러는 공개가 된다.
        return "hello";
    }

    @GetMapping("/plus/{x}")
    public int plus(
            @ApiParam(value = "x값",defaultValue = "20")//Swagger에서의 설명을 변경할수 있다
            @PathVariable int x,
            @ApiParam(value = "y값",defaultValue = "5")
            @RequestParam int y){
        return x+y;
    }

    @ApiOperation(value = "사용자의 이름과 나이를 리턴하는 메소드")
    @ApiResponse(code = 502,message = "사용자의 나이가 10살 이하일때")//502코드를 리턴
    @GetMapping("/user")
    public UserRes user(UserReq userReq){
        return new UserRes(userReq.getName(),userReq.getAge());
    }

    @PostMapping("/user")
    public UserRes userPost(@RequestBody UserReq req){
        return new UserRes(req.getName(),req.getAge());
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "x",required = true,paramType = "path",dataType = "int",value = "x 값"),//메소드 위로 빼면서 가독성이 좋아졋다
        @ApiImplicitParam(name = "y", required = true, paramType = "query",dataType = "int",value = "y 값")
    })
    @GetMapping("/minus/{x}")
    public int minus(
            //ApiParam이 많이 붙으면 코드가 지저분해진다-> 메소드에 붙힌다.
            @PathVariable int x,
            @RequestParam int y){
        return x-y;
    }
}
