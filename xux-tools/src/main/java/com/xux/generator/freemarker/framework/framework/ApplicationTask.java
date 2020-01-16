package com.xux.generator.freemarker.framework.framework;


import com.xux.generator.freemarker.framework.framework.context.ApplicationContext;

public interface ApplicationTask extends Skipable{
    boolean perform(ApplicationContext context) throws Exception;

    boolean hasNext();

    void registerNextTask(ApplicationTask nextTask);

    ApplicationTask next();
}
