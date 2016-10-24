package com.test;
 

import java.awt.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecord {
   private ScreenRecorder screenRecorder;
 
    //public static void main(String[] args) throws Exception {
           @Test
           
           public void login() throws Exception{
            VideoRecord  videoReord = new VideoRecord();
            videoReord.startRecording();                                       
                      
            WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get("http://demo.osource.co.in/ERPONEX_INT_WEBAPP/Login.aspx");
    		
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		
    		driver.findElement(By.id("Login1_UserName")).clear();
    		driver.findElement(By.id("Login1_UserName")).sendKeys("Admin CRM");
    		driver.findElement(By.id("Login1_Password")).clear();
    		driver.findElement(By.id("Login1_Password")).sendKeys("abcd");
    		
    		driver.findElement(By.id("Login1_LoginButton")).click();                                                                      
            driver.quit();                              
            videoReord.stopRecording();
      }
 

      public void startRecording() throws Exception
      {    
             File file = new File("D:\\HRMS DATA\\Videos");
                           
             Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
             int width = screenSize.width;
             int height = screenSize.height;
                            
             Rectangle captureSize = new Rectangle(0,0, width, height);
                            
           GraphicsConfiguration gc = GraphicsEnvironment
              .getLocalGraphicsEnvironment()
              .getDefaultScreenDevice()
              .getDefaultConfiguration();

          this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
              new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
              new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                   CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                   DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                   QualityKey, 1.0f,
                   KeyFrameIntervalKey, 15 * 60),
              new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                   FrameRateKey, Rational.valueOf(30)),
              null, file, "VideoCapture");
         this.screenRecorder.start();
      
      }

      public void stopRecording() throws Exception
      {
        this.screenRecorder.stop();
      }
}