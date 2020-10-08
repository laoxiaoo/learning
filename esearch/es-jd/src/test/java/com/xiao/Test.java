package com.xiao;

import cn.hutool.json.JSONUtil;
import com.xiao.entity.Goods;
import com.xiao.utils.Constant;
import com.xiao.utils.HtmlParserUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @program: learning
 * @description: TODO
 * @author: lonely xiao
 * @create: 2020-10-08 16:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {



    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ContentServiceImpl contentService;

    @org.junit.Test
    public void addContent() throws Exception {
        List<Goods> goods = HtmlParserUtils.getGoods("java");
        BulkRequest bulkRequest = new BulkRequest();

        goods.stream().forEach(g -> bulkRequest
                .add(new IndexRequest(Constant.GOODS_INDEX_NAME)
                        .source(JSONUtil.toJsonStr(g), XContentType.JSON)));
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    @org.junit.Test
    public void query() throws Exception {
        List<Map<String, Object>> java = contentService.getContent("java", 1, 3);
        System.out.println(java.toString());
    }
}