package com.moonlit.kingserp.admin.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 链接转二维码图片
 * @Author: Joshua
 * @CreateDate: 2020/7/2 11:27
 * @Version 1.0
 */
public class QrCodeUtil {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    private static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    /**
     * 将内容contents生成长宽均为width的图片，图片路径由imgPath指定
     */
    public static File getQrCodeImge(String contents, int width, String imgPath) {
        return getQRCodeImge(contents, width, width, imgPath);
    }

    /**
     * 将内容contents生成长为width，宽为width的图片，图片路径由imgPath指定
     */
    public static File getQRCodeImge(String contents, int width, int height, String imgPath) {
        try {
            BitMatrix bitMatrix = getQRCodeImgeBit(contents, width, height);
            File imageFile = new File(imgPath);
            writeToFile(bitMatrix, "png", imageFile);
            return imageFile;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将内容contents生成长为width，宽为width的二进制图片
     */
    public static BitMatrix getQRCodeImgeBit(String contents, int width, int height) {
        try {
            Map<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF8");
            return new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 链接转二维码
     *
     * @param contents 链接
     * @return 结果
     */
    public static ByteArrayInputStream initQrCode(String contents) {
        ByteArrayInputStream byteArrayInputStream = null;
        // 要生成二维码的字符串：网关地址+编号
        BufferedImage bufferedImage = QrCodeUtil.toBufferedImage(Objects.requireNonNull(QrCodeUtil.getQRCodeImgeBit(contents, 256, 256)));
        // 创建输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            // 将图像输出到输出流中。
            ImageIO.write(bufferedImage, "jpg", bos);
            byteArrayInputStream = new ByteArrayInputStream(bos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayInputStream;
    }
}
