package sample;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;

public class SetupScene {

    public void setLabel(Label label, String text, Pos position, double layoutX, double layoutY, double prefHeight, double prefWidth, Font font, Paint textColor , boolean visible, AnchorPane anchorPane){
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setPrefHeight(prefHeight);
        label.setPrefWidth(prefWidth);
        label.setFont(font);
        label.setText(text);
        label.setTextFill(textColor);
        label.setAlignment(position);
        label.setVisible(visible);
        anchorPane.getChildren().add(label);
    }

    public void setTextField(TextField textField, String promptText, Pos position, double layoutX, double layoutY, double prefHeight, double prefWidth, Font font, boolean visible, AnchorPane anchorPane){
        textField.setPromptText(promptText);
        textField.setAlignment(position);
        textField.setLayoutX(layoutX);
        textField.setLayoutY(layoutY);
        textField.setFont(font);
        textField.setPrefHeight(prefHeight);
        textField.setPrefWidth(prefWidth);
        textField.setVisible(visible);
        anchorPane.getChildren().add(textField);
    }

    public void setButton(Button button,String text, Pos position,double layoutX, double layoutY, double prefHeight, double prefWidth, Font font, boolean visible, AnchorPane anchorPane){
        button.setText(text);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setAlignment(position);
        button.setFont(font);
        button.setPrefHeight(prefHeight);
        button.setPrefWidth(prefWidth);
        button.setVisible(visible);
        anchorPane.getChildren().add(button);
    }

    public void setCircle(Circle circle, double radius, double layoutX, double layoutY, Paint colorCircle, Paint colorStroke, StrokeType strokeType, double strokeWidth, boolean visible, AnchorPane anchorPane){
        circle.setRadius(radius);
        circle.setLayoutX(layoutX);
        circle.setLayoutY(layoutY);
        circle.setFill(colorCircle);
        circle.setStroke(colorStroke);
        circle.setStrokeType(strokeType);
        circle.setStrokeWidth(strokeWidth);
        circle.setVisible(visible);
        anchorPane.getChildren().add(circle);
    }

    public void setRectangle(Rectangle rectangle, double layoutX, double layoutY, double height, double width, double arcHeight, double arcWidth, Paint colorRectangle, Paint colorStroke, double strokeWidth, StrokeType strokeType, boolean visible, AnchorPane anchorPane){
        rectangle.setLayoutX(layoutX);
        rectangle.setLayoutY(layoutY);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        rectangle.setArcHeight(arcHeight);
        rectangle.setArcWidth(arcWidth);
        rectangle.setFill(colorRectangle);
        rectangle.setStroke(colorStroke);
        rectangle.setStrokeWidth(strokeWidth);
        rectangle.setStrokeType(strokeType);
        rectangle.setVisible(visible);
        anchorPane.getChildren().add(rectangle);
    }

    public void setPolyline(Polyline polyline, double layoutX, double layoutY, Paint colorPolyline, Paint stokeColor, StrokeType strokeType,double[] points , boolean visible, AnchorPane anchorPane){
        polyline.setLayoutX(layoutX);
        polyline.setLayoutY(layoutY);
        polyline.setFill(colorPolyline);
        polyline.setStroke(stokeColor);
        polyline.setStrokeType(strokeType);
        for(int index = 0; index < points.length; index ++) {
            polyline.getPoints().add(points[index]);
        }
        polyline.setVisible(visible);
        anchorPane.getChildren().add(polyline);
    }

    public void setLine(Line line, double layoutX, double layoutY, double startX, double endX, double startY, double endY,Paint colorLine, double strokeWidth, boolean visible, AnchorPane anchorPane){
        line.setLayoutX(layoutX);
        line.setLayoutY(layoutY);
        line.setFill(colorLine);
        line.setStartX(startX);
        line.setEndX(endX);
        line.setStartY(startY);
        line.setEndY(endY);
        line.setStrokeWidth(strokeWidth);
        line.setVisible(visible);
        anchorPane.getChildren().add(line);
    }

    public void setTextArea(TextArea textArea, double layoutX, double layoutY, double prefHeight, double prefWidth, boolean editable, boolean visible, AnchorPane anchorPane){
        textArea.setLayoutX(layoutX);
        textArea.setLayoutY(layoutY);
        textArea.setPrefHeight(prefHeight);
        textArea.setPrefWidth(prefWidth);
        textArea.setEditable(editable);
        textArea.setVisible(visible);
        anchorPane.getChildren().add(textArea);
    }

    public void setImageView(ImageView imageView, double layoutX, double layoutY, double fitHeight, double fitWidth, Image image, boolean visible, AnchorPane anchorPane){
        imageView.setImage(image);
        imageView.setLayoutX(layoutX);
        imageView.setLayoutY(layoutY);
        imageView.setFitHeight(fitHeight);
        imageView.setFitWidth(fitWidth);
        imageView.setVisible(visible);
        anchorPane.getChildren().add(imageView);
    }

    public void setLine(Line line, double layoutX, double layoutY, double startX, double endX, double startY, double endY, Paint colorLine, double strokeWidth, Paint strokeColor, StrokeType strokeType, boolean visible, AnchorPane anchorPane){
        line.setLayoutX(layoutX);
        line.setLayoutY(layoutY);
        line.setStartY(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setFill(colorLine);
        line.setStrokeWidth(strokeWidth);
        line.setStrokeType(strokeType);
        line.setStroke(strokeColor);
        line.setVisible(visible);
        anchorPane.getChildren().add(line);
    }

    public void setSlider(Slider slider,double layoutX, double layoutY, double prefHeight, double prefWidth, double min, double max, double valueInit, boolean visible, AnchorPane anchorPane){
        slider.setLayoutX(layoutX);
        slider.setLayoutY(layoutY);
        slider.setPrefWidth(prefWidth);
        slider.setPrefHeight(prefHeight);
        slider.setMax(max);
        slider.setMin(min);
        slider.setValue(valueInit);
        slider.setVisible(visible);
        anchorPane.getChildren().add(slider);
    }
}
