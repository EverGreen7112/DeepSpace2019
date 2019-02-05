/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.spikes2212.dashboard.DashBoardController;
import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.utils.CamerasHandler;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI oi;
  public static TankDrivetrain drivetrain;
  public static int zoomLevel;
  SendableChooser<Command> chooser = new SendableChooser<>();
  VideoSource cameraA;
  public static CamerasHandler cameraHandler;

  DashBoardController dbc;

  @Override
  public void robotInit() {
   // drivetrain = new TankDrivetrain(SubsystemComponents.DriveTrain.leftMotorGroup::set, SubsystemComponents.DriveTrain.rightMotorGroup::set);
   // drivetrain.setDefaultCommand(new DriveTank(drivetrain, oi::getLeftJoystick, oi::getRightJoystick));
    cameraHandler = new CamerasHandler(SubsystemConstants.cameras.kCamerawidth, SubsystemConstants.cameras.kCameraHeight, RobotMap.cameraA, RobotMap.cameraB);
    cameraHandler.setExposure(SubsystemConstants.cameras.kCameraExposure);
    zoomLevel = 1;
    dbc = new DashBoardController();
    new Thread(() -> {
      UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
      camera.setResolution(SubsystemConstants.cameras.kCamerawidth, SubsystemConstants.cameras.kCameraHeight);
      
      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("CameraView", SubsystemConstants.cameras.kCamerawidth, SubsystemConstants.cameras.kCameraHeight);
      
      Mat source = new Mat();
      Mat output = new Mat();

      while(!Thread.interrupted()) {
          cvSink.grabFrame(source);
          Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
          /*BufferedImage bufferedFrame = mat2Img(output);
          int zoomWidth = output.width()*zoomLevel;
          int zoomHeight = output.height()*zoomLevel;
          BufferedImage zoomedImage = new BufferedImage(zoomWidth, zoomHeight, SubsystemConstants.cameras.kCameraImageType);
          Graphics2D g = zoomedImage.createGraphics();
          g.drawImage(bufferedFrame, 0, 0, zoomWidth, zoomHeight, null);
          g.dispose();
          outputStream.putFrame(img2Mat(zoomedImage));*/
          outputStream.putFrame(output);


      }
  }).start();

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
   
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }

  public static BufferedImage mat2Img(Mat in)
  {
    BufferedImage out;
    byte[] data = new byte[320 * 240 * (int)in.elemSize()];
    int type;
    in.get(0, 0, data);

    if(in.channels() == 1)
        type = BufferedImage.TYPE_BYTE_GRAY;
    else
        type = BufferedImage.TYPE_3BYTE_BGR;

    out = new BufferedImage(320, 240, type);

    out.getRaster().setDataElements(0, 0, 320, 240, data);
    return out;
  } 
  public static Mat img2Mat(BufferedImage in)
  {
    Mat out;
    byte[] data;
    int r, g, b;

    if(in.getType() == BufferedImage.TYPE_INT_RGB)
    {
      out = new Mat(240, 320, CvType.CV_8UC3);
      data = new byte[320 * 240 * (int)out.elemSize()];
      int[] dataBuff = in.getRGB(0, 0, 320, 240, null, 0, 320);
      for(int i = 0; i < dataBuff.length; i++)
      {
        data[i*3] = (byte) ((dataBuff[i] >> 16) & 0xFF);
        data[i*3 + 1] = (byte) ((dataBuff[i] >> 8) & 0xFF);
        data[i*3 + 2] = (byte) ((dataBuff[i] >> 0) & 0xFF);
      }
    }
    else
    {
      out = new Mat(240, 320, CvType.CV_8UC1);
      data = new byte[320 * 240 * (int)out.elemSize()];
      int[] dataBuff = in.getRGB(0, 0, 320, 240, null, 0, 320);
      for(int i = 0; i < dataBuff.length; i++)
      {
        r = (byte) ((dataBuff[i] >> 16) & 0xFF);
        g = (byte) ((dataBuff[i] >> 8) & 0xFF);
        b = (byte) ((dataBuff[i] >> 0) & 0xFF);
        data[i] = (byte)((0.21 * r) + (0.71 * g) + (0.07 * b)); //luminosity
      }
    }
      out.put(0, 0, data);
      return out;
  } 
}
