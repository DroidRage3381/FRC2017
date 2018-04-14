// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3381.Robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3381.Robot.Robot;
import org.usfirst.frc3381.Robot.RobotMap;


public class AutoStraightGyro extends Command {
	
	Double baseSpeed;
	Double maxSpeed;
	Double distance;
	
	//double last_world_linear_accel_x;
   // double last_world_linear_accel_y;
   // double kCollisionThreshold_DeltaG;
	
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

   
    public AutoStraightGyro(Double distance) {

    	this.distance = distance;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetEncoder();
    	
    	baseSpeed = RobotMap.prefs.getDouble( "Base Speed", 0.55);
    	maxSpeed = RobotMap.prefs.getDouble( "Max Speed", 0.7);
    	
    	//kCollisionThreshold_DeltaG = 1.0;
    	//last_world_linear_accel_x = RobotMap.navx.getWorldLinearAccelX();
    	//last_world_linear_accel_y = RobotMap.navx.getWorldLinearAccelY();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Turn Angle", Robot.driveTrain.getHeading());
    	SmartDashboard.putNumber("Drive Encoder", Robot.driveTrain.getDistance());
    	
    	Double heading = Robot.driveTrain.getHeading();
    	
    	if (baseSpeed <= maxSpeed){
    		baseSpeed = (baseSpeed + 0.005);
    	}
    	
    	if (distance - 10 <= Robot.driveTrain.getDistance() && baseSpeed >= 0.55){
    		baseSpeed = (baseSpeed - 0.02);
    	}
    	
    	Double left = (baseSpeed + (0.025 * heading));
    	Double right = (baseSpeed - (0.025 * heading));
    	
    	
    	SmartDashboard.putNumber("Base Speed", baseSpeed);

    	Robot.driveTrain.tankDrive(left, right);
    	
    	if (Robot.driveTrain.hasGear()){
    		Robot.driveTrain.enableGreenLED();
    	}
    	
    	else {
    		Robot.driveTrain.disableGreenLED();
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
//   	 double curr_world_linear_accel_x = RobotMap.navx.getWorldLinearAccelX();
//     double currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
//     last_world_linear_accel_x = curr_world_linear_accel_x;
//     double curr_world_linear_accel_y = RobotMap.navx.getWorldLinearAccelY();
//     double currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
//     last_world_linear_accel_y = curr_world_linear_accel_y;
//     
//	boolean collisionDetected = false;
//	
//	if ( ( Math.abs(currentJerkX) > kCollisionThreshold_DeltaG ) ||
//            ( Math.abs(currentJerkY) > kCollisionThreshold_DeltaG) ) {
//           collisionDetected = true;
//       }
//	SmartDashboard.putBoolean(  "CollisionDetected", collisionDetected);
//	SmartDashboard.putNumber("Current Jerk X Value", currentJerkX);
//	SmartDashboard.putNumber("Current Jerk Y Value", currentJerkY);
    	
        return Robot.driveTrain.getDistance() > distance; //|| collisionDetected;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.driveTrain.setToCero();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    	
    }
}