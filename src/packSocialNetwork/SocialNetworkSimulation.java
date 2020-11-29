package packSocialNetwork;


/**
 * SocialNetworkSimulation simulates how the packSocialNetwork.SocialNetwork works.
 * This project is being developed on Data Structures and Algorithms subject on UPV/EHU at 2020/2021 academic year.
 *
 * @author Iyán Álvarez and Davy Wellinger
 * @version secondMilestone
 */
public class SocialNetworkSimulation {

    public static void main(String[] args) {
        SocialNetwork sn = SocialNetwork.getInstance();
        sn.initialMenu();
    }
}
