package Observer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("AspectJ Observer Example");
        JPanel panel = new JPanel();

        Subject subject = new Subject();

        Observer observer = new ColorObserver(subject, panel);
        subject.attach(observer);

        JButton redButton = new JButton("Red");
        JButton greenButton = new JButton("Green");
        JButton blueButton = new JButton("Blue");

        redButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject.setState("Red");
                subject.notifyAllObservers();
            }
        });

        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject.setState("Green");
                subject.notifyAllObservers();
            }
        });

        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject.setState("Blue");
                subject.notifyAllObservers();
            }
        });

        panel.add(redButton);
        panel.add(greenButton);
        panel.add(blueButton);

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ColorObserver extends Observer {
    private JPanel panel;

    public ColorObserver(Subject subject, JPanel panel) {
        this.subject = subject;
        this.panel = panel;
    }

    @Override
    public void update() {
        String state = subject.getState();
        switch (state) {
            case "Red":
                panel.setBackground(Color.RED);
                break;
            case "Green":
                panel.setBackground(Color.GREEN);
                break;
            case "Blue":
                panel.setBackground(Color.BLUE);
                break;
        }
        System.out.println("Background color changed to: " + state);
    }
}
