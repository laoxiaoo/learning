package com.xiao;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PageMode;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitWidthDestination;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiao jie
 * @create 2022年06月24日 16:22:00
 */
public class PdfMergeTest {

    @Test
    public void test() throws Exception {
        List<File> files = FileUtil.loopFiles("D:\\BaiduNetdiskDownload\\94-DDD实战课");
        System.out.println(files);
        files = files.stream().filter(var -> var.getName().contains("pdf")).collect(Collectors.toList());
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        pdfMergerUtility.setDestinationFileName("D:\\BaiduNetdiskDownload\\94-DDD实战课\\DDD实战课.pdf");
        for(File file : files) {
            pdfMergerUtility.addSource(file);
        }
        pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        System.out.println("Documents merged");

        PDDocument document = PDDocument.load(new File("D:\\BaiduNetdiskDownload\\94-DDD实战课\\DDD实战课.pdf"));

        PDDocumentOutline documentOutline = new PDDocumentOutline();
        document.getDocumentCatalog().setDocumentOutline(documentOutline);
        PDOutlineItem pagesOutline = new PDOutlineItem();
        pagesOutline.setTitle("DDD 教程");
        documentOutline.addLast(pagesOutline);
        int j = 0;
        int nextPage=0;
        //添加书签
        for(int i = 0; i < document.getNumberOfPages(); i++) {
            if(i == nextPage) {
                File file = files.get(j);
                PDDocument load = PDDocument.load(file);
                int pages = load.getNumberOfPages();
                nextPage = nextPage+pages;
                PDPageDestination pageDestination = new PDPageFitWidthDestination();
                //设置当前书签所在页码
                pageDestination.setPage(document.getPage(i));
                PDOutlineItem bookmark = new PDOutlineItem();
                bookmark.setDestination(pageDestination);
                bookmark.setTitle(StrUtil.strip(files.get(j).getName(), ".pdf"));
                pagesOutline.addLast(bookmark);
                j++;
                load.close();
            }
        }

        pagesOutline.openNode();
        documentOutline.openNode();

        document.getDocumentCatalog().setPageMode(PageMode.USE_OUTLINES);
        document.save("D:\\BaiduNetdiskDownload\\94-DDD实战课\\DDD实战课Temp.pdf");
        document.close();
    }

}
