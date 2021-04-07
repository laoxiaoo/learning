package com.xiao;

import cn.hutool.core.io.FileUtil;
import org.jodconverter.DocumentConverter;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * @author 肖杰
 * @ClassName PdfConverTest.java
 * @Description
 * @createTime 2021年04月06日 10:24:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfConverterTest {

    @Autowired
    private DocumentConverter converter;

    @Test
    public void word2pdf() {
        try {
            String wordPath = "D:\\企业授权书.docx";
            File file = new File( "d:\\111123143.pdf");
            //converter.convert(new File(wordPath)).to(file).as(DefaultDocumentFormatRegistry.PDF).execute();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            converter.convert(FileUtil.getInputStream(new File(wordPath)))
                    .as(DefaultDocumentFormatRegistry.PNG).to(outputStream)
                    .as(DefaultDocumentFormatRegistry.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
