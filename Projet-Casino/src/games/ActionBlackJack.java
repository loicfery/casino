package games;

import java.util.List;

public interface ActionBlackJack {
    void action(List<UserHand> ListOfUserHand, Cards_Package cards_package, Bet bet);
}
