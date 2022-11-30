package com.xux.imc.template.api;

import com.xux.imc.template.api.vo.SimpleVO;
import com.xux.imc.template.api.rest.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "例子api", consumes = "application/json", tags = "SimpleApi", produces = "application/json")
@RequestMapping("/simple")
public interface SimpleApi {

    @ApiOperation(value = "新增", notes = "新增", response = Result.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "成功", response = Result.class)})
    @RequestMapping(value = "add", method = RequestMethod.POST)
    Result add(SimpleVO vo) throws Exception;

    @ApiOperation(value = "新增2", notes = "新增2", response = Result.class)
    @ApiResponses(value = {@ApiResponse(code = 0, message = "成功", response = Result.class)})
    @RequestMapping(value = "v2/add", method = RequestMethod.POST)
    void addv2(SimpleVO vo) throws Exception;
}
