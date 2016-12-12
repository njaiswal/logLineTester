package com.nj.utils;

import com.nj.app.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    public void doSomethingUsefull(String msg){
        logger.info("Message: {}", msg);

    }
}
