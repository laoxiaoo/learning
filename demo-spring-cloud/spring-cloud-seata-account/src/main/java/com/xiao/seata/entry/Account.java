package com.xiao.seata.entry;

public class Account {
    private Long id;

    private Long userId;

    private Integer total;

    private Integer reside;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getReside() {
        return reside;
    }

    public void setReside(Integer reside) {
        this.reside = reside;
    }
}
