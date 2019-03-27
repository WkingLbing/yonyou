package com.yonyou.util;

import org.apache.log4j.Logger;
import org.junit.Test;

public class log4j {
	Logger logger= Logger.getLogger(log4j.class);
    @Test
    public void printLogger(){
        logger.error("直接输出吧");
    }
}
