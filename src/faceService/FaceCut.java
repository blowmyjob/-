package faceService;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
/*
@param lastDir
*            后缀
* @param srcPath
*            图片地址
* @param x
*            开始剪切的x坐标
* @param y
*            开始剪切的y坐标
* @param width
*            需要剪切的宽
* @param height
*            需要剪切的高
* @param subPath
*            保存图片地址
*/

public class FaceCut {
	public static void cutPicture(String lastDir, String srcPath, int x, int y, int width, int height, String subPath)
			throws IOException {

		FileInputStream is = null;
		ImageInputStream iis = null;

		try {
			is = new FileInputStream(srcPath);
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(lastDir);
			ImageReader reader = it.next();

			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);

			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);

			ImageIO.write(bi, lastDir, new File(subPath));
		} finally {
			if (is != null)
				is.close();
			if (iis != null)
				iis.close();
		}
	}

}


