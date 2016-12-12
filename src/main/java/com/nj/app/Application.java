package com.nj.app;

import com.nj.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {

        logger.info("I am in main with args:{}", args);

        Utils utils = new Utils();
        Arrays.asList("One", "Two", "Three").forEach(utils::doSomethingUsefull);
    }
}
