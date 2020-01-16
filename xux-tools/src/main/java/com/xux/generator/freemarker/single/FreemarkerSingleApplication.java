package com.xux.generator.freemarker.single;

import com.xux.generator.freemarker.single.util.CodeGenerateUtils;

public class FreemarkerSingleApplication {

    public static void main(String[] args) throws Exception{
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        codeGenerateUtils.generate();
    }
}
