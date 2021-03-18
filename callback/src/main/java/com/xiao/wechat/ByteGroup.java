package com.xiao.wechat;

import java.util.ArrayList;

/**
 * @author 肖杰
 * @version 3.0
 * @ClassName ByteGroup.java
 * @Description
 * @createTime 2021年01月11日 16:20:00
 */

class ByteGroup {
    ArrayList<Byte> byteContainer = new ArrayList<Byte>();

    public byte[] toBytes() {
        byte[] bytes = new byte[byteContainer.size()];
        for (int i = 0; i < byteContainer.size(); i++) {
            bytes[i] = byteContainer.get(i);
        }
        return bytes;
    }

    public ByteGroup addBytes(byte[] bytes) {
        for (byte b : bytes) {
            byteContainer.add(b);
        }
        return this;
    }

    public int size() {
        return byteContainer.size();
    }
}
