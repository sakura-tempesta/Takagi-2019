/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6909.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */


public class Robot extends IterativeRobot {
	private XboxController driver;
	private RWMTalonSRX m_leftArm;
	private RWMTalonSRX m_rightArm;
	private Spark m_leftFront, m_leftRear;
	private Spark m_rightFront, m_rightRear;
	private SpeedControllerGroup m_left;
	private SpeedControllerGroup m_right;
	private DifferentialDrive drive;
	
	Robot() {
		driver = new XboxController(0);
		
		m_leftArm = new RWMTalonSRX(5);
		m_rightArm = new RWMTalonSRX(6);
		
		m_leftFront = new Spark(0);
		m_leftRear = new Spark(1);
		m_rightFront = new Spark(2);
		m_rightRear = new Spark(3);
		m_left = new SpeedControllerGroup(m_leftFront, m_leftRear);
		m_right = new SpeedControllerGroup(m_rightFront, m_rightRear);
		drive = new DifferentialDrive(m_left, m_right);
	}
	
	public void telelopPeriodic() {
		double value = driver.getY(Hand.kLeft);
		
		m_leftArm.set(value);
		m_rightArm.set(-value);
		
//		 不感帯の設定
//		XかYのどちらかがしきい値を超えたら動かす
		
		if (Math.abs(driver.getY(Hand.kLeft)) > 0.2 || Math.abs(driver.getX(Hand.kRight)) > 0.2) {
			drive.arcadeDrive(driver.getY(Hand.kLeft), driver.getX(Hand.kRight));
		} else {
//			触っていないときはロボットを止める
			drive.arcadeDrive(0.0, 0.0);
		}
	}
	
//	不感帯
//	public double getX(GenericHID.Hand hand);
//	public double getY(GenericHID.Hand hand);
//	if -0.2 < getX(GenericHID.Hand hand) < 0.2 || -0.2 < getY(GenericHID.Hand hand) < 0.2 {
//		m_leftArm(0.0, 0.0);
//		m_rightArm(0.0, 0.0);
//	} else {
//		m_leftArm(getX(GenericHID.Hand hand), getY(GenericHID.Hand hand));
//		m_rightArm(getX(GenericHID.Hand hand), getY(GenericHID.Hand hand));
//	}
//	
//	public abstract boolean get（）{
//		
//		
//		
//	}
}
