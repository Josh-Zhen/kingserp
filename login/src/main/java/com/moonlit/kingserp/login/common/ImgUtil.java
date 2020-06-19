package com.moonlit.kingserp.login.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

/**
 * @Description: 圖片格式轉換
 * @Author: Joshua
 * @CreateDate: 2020-06-19 9:57
 * @Version 1.0
 */
public class ImgUtil {
    public static final String JPG = "jpg";
    public static final String GIF = "gif";
    public static final String PNG = "png";
    public static final String BMP = "bmp";


    /**
     * inputFormat表示原格式，outputFormat表示转化后的格式
     */
    public void conversion(String inputFormat, String outputFormat, String src) {

        try {
            File input = new File(src + inputFormat);
            BufferedImage bim = ImageIO.read(input);
            File output = new File(src + outputFormat);
            ImageIO.write(bim, outputFormat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String src = "E:/2.";
        //JPG转成PNG
        new ImgUtil().conversion(JPG, PNG, src);
        //JPG转成GIF
        new ImgUtil().conversion(JPG, GIF, src);
        //其余格式转化只要调用Conversion函数即可
    }
}