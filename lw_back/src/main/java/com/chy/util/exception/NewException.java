package com.chy.util.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: NewException
 * Package: com.chy.util.exception
 *
 * @Author: Golden
 * @Create: 2024/6/18
 * Description:
 */

@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class NewException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
