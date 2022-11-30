package com.xux.imc.template.controller;


import com.xux.imc.template.api.SimpleApi;
import com.xux.imc.template.api.rest.Result;
import com.xux.imc.template.api.vo.SimpleVO;
import com.xux.imc.template.service.SimpleSV;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SimpleController implements SimpleApi {

    final private SimpleSV simpleSV;

    @Override
    public Result add(SimpleVO vo) throws Exception {
        return null;
    }

    @Override
    public void addv2(SimpleVO vo) throws Exception {

    }
}
