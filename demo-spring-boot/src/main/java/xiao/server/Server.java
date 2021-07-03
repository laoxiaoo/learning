package xiao.server;

/**
 * @author 肖杰
 * @version 1.
 * @ClassName Server.java
 * @Description TODO
 * @createTime 2021年03月23日 09:04:00
 */
public interface Server {

    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();

    enum Type {
         FTP,
        HTTP,
        ;
    }
}
