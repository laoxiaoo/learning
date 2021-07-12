package com.xiao.path;

import org.junit.Test;

import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

/**
 * @author malone xiao
 * @ClassName TestPath.java
 * @Description
 *
 *  path类的常用 方法
 *
 * @createTime 2021年05月20日 16:12:00
 */
public class TestPath {
    static Path test = Paths.get("src/main/java/com/xiao/path");
    static Path testTmp = test.toAbsolutePath();

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));

        System.out.println(Paths.get("..", "..", "..").toAbsolutePath().normalize());
    }

    @Test
    public void test1() {
        //toAbsolutePath：获取项目上两层的目录
        //normalize：将相对路径转为绝对路径
        System.out.println(Paths.get("..", "..").toAbsolutePath().normalize());
    }

    @Test
    public void test2() {
        Path path = Paths.get("D:\\git\\gitee\\learning\\stu-java-base\\src\\main\\java\\com\\xiao\\path\\TestPath.java");
        //获取项目的src的绝对路径
        System.out.println(path.toString());


        for(int i=0; i<path.getNameCount(); i++) {
            System.out.println(path.getName(i));
        }
    }

    @Test
    public void test3() {
        //D:\git\gitee
        Path path = Paths.get("..", "..").toAbsolutePath().normalize();
        //D:\git\gitee\learning\stu-java-base\TestPath.java
        Path path1 = Paths.get("TestPath.java").toAbsolutePath();
        //learning\stu-java-base\TestPath.java
        System.out.println(path.relativize(path1));
    }

    @Test
    public void test4() {
        Path resolve = test.resolve("test.txt");
        System.out.println(resolve);
    }

    @Test
    public void test5() {
        System.out.println(testTmp);

        System.out.println(testTmp.startsWith("D:\\"));;
    }

    @Test
    public void watchPath() throws Exception {
        //监听这个文件夹下的文件
        Path resolve = testTmp.resolve("test");
        WatchService watcher = FileSystems.getDefault().newWatchService();
        resolve.register(watcher, ENTRY_DELETE);
        //watcher.take() 将等待并阻塞在这里。当目标事件发生时，会返回一个包含 WatchEvent 的 Watchkey 对象
        WatchKey key = watcher.take();
        for(WatchEvent evt : key.pollEvents()) {
            System.out.println("evt.context(): " + evt.context() +
                    "\nevt.count(): " + evt.count() +
                    "\nevt.kind(): " + evt.kind());
            System.exit(0);
        }
    }

    /**
     *  查找文件
     */
    @Test
    public void searchFile() throws Exception {
        //按照glob匹配，也可以regex匹配
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.java");
        Files.walk(test).filter(pathMatcher::matches).forEach(System.out::println);
    }

    @Test
    public void readFile() throws Exception {
        PathMatcher pathMatcher = FileSystems
                .getDefault()
                .getPathMatcher("glob:**/TestPath.java");
        Files.readAllLines(Files.walk(test)
                .filter(pathMatcher::matches)
                .findFirst().orElse(null))
                .stream()
                .forEach(System.out::println);
    }


}
