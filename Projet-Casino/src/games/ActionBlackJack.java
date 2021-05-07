package games;

import java.util.List;

public interface ActionBlackJack {
    void action(List<UserHand> ListOfUserHand, CardPackage card_package, Bet bet);
}
