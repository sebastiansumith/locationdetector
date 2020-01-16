package com.suse.demo;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
		FFmpegFrameGrabber g = new FFmpegFrameGrabber("/Users/sumith/Downloads/test.mp4");
		g.start();

		int timeStamp = 10000000;
		while(true){
			g.setTimestamp(timeStamp);
			Frame frame = g.grabImage();
			BufferedImage image = new Java2DFrameConverter().convert(frame);

			ImageIO.write(image, "png", new File("/Users/sumith/Desktop/fileCapture/video-frame-" + System.currentTimeMillis() + ".png"));
			timeStamp += 10000000;
			if(timeStamp> g.getLengthInTime()){
				break;
			}
		}

		g.stop();
	}
}
