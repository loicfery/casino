package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MessageInterface {

    public void setMessage(Label label, String message, Color color){
        Timeline timeline = new Timeline();
        Duration timePoint = Duration.ZERO;

        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> label.setText(message)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> label.setTextFill(color)));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> label.setVisible(true)));
        timePoint = timePoint.add(Duration.seconds(3));
        timeline.getKeyFrames().add(new KeyFrame(timePoint, e -> label.setVisible(false)));

        timeline.play();
    }
}
