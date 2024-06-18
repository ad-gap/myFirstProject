package com.chy.controller;

import com.chy.util.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: TestController
 * Package: com.chy.controller
 *
 * @Author: Golden
 * @Create: 2024/6/17
 * Description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PreAuthorize("hasAuthority('BOOK_VIEW')")
    @GetMapping("/read")
    public Result read(){
        return Result.success("read success");
    }


    @PreAuthorize("hasAuthority('BOOK_ADD')")
    @GetMapping("/add")
    public Result add() {
        return Result.success("add success");
    }


    @PreAuthorize("hasAuthority('BOOK_DELETE')")
    @GetMapping("/delete")
    public Result delete() {
        return Result.success("delete success");
    }

    @PreAuthorize("hasAuthority('BOOK_EDIT')")
    @GetMapping("/update")
    public Result update() {
        return Result.success("update success");
    }

}
