package com.xiao.flow;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * 响应式流的测试与实现
 *
 * @author xiao ji hao
 * @create 2021年07月03日 16:24:00
 */
public class FlowMain {

    public static void main(String[] args) throws Exception {
        //定义一个发布者，发布数据
        //此处使用jdk9自带的，他实现了Publisher接口
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        //定义一个订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                //建立订阅关系的时候调用
                //保存订阅关系，用于后面想发布者请求数据
                this.subscription = subscription;
                //向发布者请求一条数据
                subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                //接收到数据
                System.out.println(item);
                //获取一条数据后向服务器端请求数据
                subscription.request(1);
                //也可以告诉发布者不再接受数据
                //subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                //发生异常调用
                System.out.println(throwable);
            }

            @Override
            public void onComplete() {
                //发布者关闭时候调用
                System.out.println("处理完了....");
            }
        };
        //发布者与订阅者产生关系
        publisher.subscribe(subscriber);
        //发布数据
        publisher.submit(1);
        publisher.submit(2);
        //关闭发布者
        publisher.close();
        Thread.sleep(10000);
    }

}
