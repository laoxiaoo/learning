package com.xiao;


import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.jupiter.api.Test;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lao xiao
 * @date 2024-11-21 16:13
 */
@Slf4j
public class ImageToPdf {

    @Test
    public void test1() throws Exception {
        //String imageDir = "D:\\BaiduNetdiskDownload\\第001-050话 神精榜(知音漫客)\\第000章 1话"; // 替换为你的图片目录路径
        String outputPDF = "D:\\BaiduNetdiskDownload\\第001-050话 神精榜(知音漫客)\\output.pdf"; // 输出PDF文件的名称
        String imageDir = "D:\\BaiduNetdiskDownload\\第001-050话 神精榜(知音漫客)";


        Path dir = Paths.get(imageDir);
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir)) {
            int i=1;
            int j=1;
            PDDocument doc = null;
            try {
                doc = new PDDocument();
                for (Path path : directoryStream) {
                    List<Path> pathList = Files.walk(path)
                            .filter(p -> p.toString().endsWith(".jpg") || p.toString().endsWith(".png")).collect(Collectors.toList());
                    for (Path p : pathList) {
                        setImage(p, doc);
                    }
                    if (i==10) {
                        doc.save(path.toFile().getAbsolutePath()+"神精榜 第"+j+"话 "+ path.getFileName() + ".pdf");
                        System.out.println(path.toFile().getAbsolutePath()+"神精榜 第"+j+"话.pdf");
                        System.out.println(path.getFileName());
                        doc.close();
                        doc = new PDDocument();
                        i=0;
                    }
                    i++;
                    j++;
                }
                if (i > 1) {
                    doc.save(imageDir+"\\神精榜 第"+j+"话 .pdf");
                    System.out.println(imageDir+"\\神精榜 第"+j+"话 .pdf");
                    doc.close();
                }
            } catch (Exception e) {
                log.error("invoke test1 error  ", e);
            } finally {
                doc.close();
            }
        } catch (IOException | DirectoryIteratorException e) {
            System.err.println("Error occurred while reading directory: " + e.getMessage());
        }
//        Files.walk(dir).forEach(path -> {

//        });

    }

    private static void setImage(Path path, PDDocument doc) {
        try {
            PDImageXObject pdImage = PDImageXObject.createFromFile(path.toString(), doc);
            // 获取图像的原始尺寸
            float imageWidth = pdImage.getWidth();
            float imageHeight = pdImage.getHeight();
            PDPage page = new PDPage(new PDRectangle(imageWidth, imageHeight));
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);

            // 获取页面的尺寸
            float pageSizeWidth = page.getMediaBox().getWidth();
            float pageSizeHeight = page.getMediaBox().getHeight();


            // 计算缩放比例，保持图像的宽高比
            float scaleX = pageSizeWidth / imageWidth;
            float scaleY = pageSizeHeight / imageHeight;
            float scale = Math.min(scaleX, scaleY);

            // 计算缩放后的图像尺寸
            float scaledWidth = imageWidth * scale;
            float scaledHeight = imageHeight * scale;

            // 计算图像在页面上的位置，使其居中
            float positionX = (pageSizeWidth - scaledWidth) / 2;
            float positionY = (pageSizeHeight - scaledHeight) / 2;

            contentStream.drawImage(pdImage, positionX, positionY, imageWidth, imageHeight);
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
