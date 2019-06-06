/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public interface Exceptions {
    
    public static class ElevatorOutOfRangeException extends Exception {

        private static final long serialVersionUID = 1L;

        public ElevatorOutOfRangeException(String message)
        {
            super(message);       
        }

        public ElevatorOutOfRangeException(String message, Throwable error)
        {
            super(message, error);
        }
    }
}
