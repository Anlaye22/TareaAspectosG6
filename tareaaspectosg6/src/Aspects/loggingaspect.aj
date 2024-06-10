package Aspects;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public aspect loggingaspect {
	pointcut stateChange(): execution(* observer.Subject.setState(..));

    after(): stateChange() {
        String state = (String) thisJoinPoint.getArgs()[0];
        try (PrintWriter out = new PrintWriter(new FileWriter("colors.log", true))) {
            out.println("Background color changed to: " + state);
            System.out.println("Log written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
