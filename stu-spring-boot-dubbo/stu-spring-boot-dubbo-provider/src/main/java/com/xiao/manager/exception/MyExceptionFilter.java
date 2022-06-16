package com.xiao.manager.exception;

import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author xiao jie
 * @create 2022年05月18日 10:23:00
 */
public class MyExceptionFilter implements Filter, Filter.Listener  {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
            Throwable exception = appResponse.getException();
            if(exception instanceof RuntimeException) {
                appResponse.setException(null);
                appResponse.setValue(exception.getMessage());
            }
        }
    }

    @Override
    public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {

    }
}
