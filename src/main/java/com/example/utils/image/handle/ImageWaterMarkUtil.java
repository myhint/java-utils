package com.example.utils.image.handle;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @Description 图片水印工具类
 * 1）图片水印
 * 2）文字水印
 * 3）混合图文水印
 * @Author blake
 * @Date 2019-02-19 11:22
 * @Version 1.0
 */
public class ImageWaterMarkUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageWaterMarkUtil.class);

    public static void main(String[] args) throws IOException {
        // 添加图片水印
        // generatePicWaterMark();

        // 添加文字水印
        File srcImgFile = new File("zl.jpeg");
        File outputImageFile = new File("watermark-text.jpg");
        String logoText = "星辰大海";

        generateTextWaterMark(srcImgFile, logoText, outputImageFile, 0);
    }

    /**
     * @return void
     * @throws
     * @description 添加图片水印
     * @params []
     */
    private static void generatePicWaterMark() throws IOException {

        // 原图
        File originFile = new File("zl.jpeg");
        // 水印图片
        File waterMarkImageFile = new File("watermark.png");
        // 成品图片
        File generateFile = new File("now.jpg");

        BufferedImage image = null;
        try {
            image = ImageIO.read(originFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Objects.isNull(image)) {
            logger.info("ImageWaterMarkUtil.generatePicWaterMark ========= 图片渲染失败 ========= ");
            return;
        }

        Thumbnails.of(originFile)
                .size(image.getWidth(), image.getHeight())
                .rotate(30)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(waterMarkImageFile), 0.5f)
                .outputQuality(0.8)
                .toFile(generateFile);
    }

    /**
     * @return void
     * @throws
     * @description 添加文字水印
     * @params [srcImgFile, logoText, outputImageFile, degree]
     */
    public static void generateTextWaterMark(File srcImgFile, String logoText, File outputImageFile, double degree) {

        OutputStream os = null;

        try {
            Image srcImg = ImageIO.read(srcImgFile);

            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

            Graphics2D graphics = buffImg.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null),
                    srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);

            if (degree > 0) {
                graphics.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2,
                        (double) buffImg.getHeight() / 2);
            }
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("宋体", Font.BOLD, 36));

            float alpha = 0.8f;
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));

            // TODO 调整文字位置
            graphics.drawString(logoText, buffImg.getWidth() / 3, buffImg.getHeight() / 2);
            graphics.dispose();

            os = new FileOutputStream(outputImageFile);

            // 生成图片
            ImageIO.write(buffImg, "JPG", os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(os))
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
