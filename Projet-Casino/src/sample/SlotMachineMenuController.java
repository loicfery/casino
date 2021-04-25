package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class SlotMachineMenuController {
    public Rectangle slot1;
    public Rectangle slot2;
    public Rectangle slot3;

    public ImageView pictureSLot1;
    public ImageView pictureSlot2;
    public ImageView pictureSlot3;

    public ImageView pictureSevenSlot1;
    public ImageView pictureCherry1Slot1;
    public ImageView pictureCherry2Slot1;
    public ImageView pictureLemon1Slot1;


    public Button startingGameButton;

    public void switchPicture(int numberOfPicture, int slot){
        switch (slot){
            case 1 : pictureSLot1.setVisible(false); switch (numberOfPicture){
                case 0 : pictureSevenSlot1.setVisible(true); pictureSLot1 = pictureSevenSlot1; break;
                case 1 : pictureCherry1Slot1.setVisible(true); pictureSLot1 = pictureCherry1Slot1;
                case 3 : pictureCherry2Slot1.setVisible(true); pictureSLot1 = pictureCherry2Slot1; break;
                case 4 : pictureLemon1Slot1.setVisible(true); pictureSLot1 = pictureCherry1Slot1; break;
            }
        }
    }

    public void clickStartingGame(ActionEvent actionEvent){
        switchPicture(1,1);

       /* int position = 0;
        for(int index = 0; index < 10; index ++){
            switchPictureSlot1(position);
            position = (position + 1) % 4;
            System.out.println("new position : "+position);

            //double time = System.currentTimeMillis();
            //while(5000 > (System.currentTimeMillis() - time)){}

            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

    public void clickTest(MouseEvent mouseEvent) {

    }
}
