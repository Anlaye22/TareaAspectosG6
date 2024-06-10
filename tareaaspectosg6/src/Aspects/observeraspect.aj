package Aspects;

import javax.security.auth.Subject;

public aspect observeraspect {
	pointcut stateChange(Subject subject): execution(* observer.Subject.setState(..)) && this(subject);

    after(Subject subject): stateChange(subject) {
        subject.notifyAll();
        System.out.println("ObserverAspect triggered for state change."); 
    }
}
