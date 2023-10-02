package com.xiao.redis.sub.cache;

/**
 * @author xiao jie
 * @date 2023-10-02 10:18
 */
public class Command {

    public final static byte OPT_JOIN 	   = 0x01;	//加入集群
    public final static byte OPT_LEVEL_TREE = 0x02; 	//更改层级树

    private int operator;

    public Command(byte b) {
        operator = b;
    }

    public int getOperator() {
        return operator;
    }

    public static Command join() {
        return new Command(OPT_JOIN);
    }


}
