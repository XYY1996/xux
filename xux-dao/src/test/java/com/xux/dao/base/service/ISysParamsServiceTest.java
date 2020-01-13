package com.xux.dao.base.service;

import com.xux.dao.base.entity.SysParams;
import com.xux.dao.base.mapper.SysParamsMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ISysParamsServiceTest {

    @Autowired
    private ISysParamsService service;

    @Autowired
    private SysParamsMapper mapper;

    @Test
    public void testInsert(){
        SysParams params= new SysParams();
        params.setName("sdd");
        params.setLang("111");
        params.setRemark("sdf");
        params.setValue("value");
        assertTrue(service.save(params));
        log.error(params.toString());
    }

    @Test
    public void testSelect(){
        SysParams params = mapper.selectByPrimaryKey("RADAR_MIGU_YUN_IS_TRANSFORM_FILE_URL");
        log.error(params.toString());
    }
}