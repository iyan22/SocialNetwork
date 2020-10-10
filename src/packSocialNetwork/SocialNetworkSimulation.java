package packSocialNetwork;

/**
 * SocialNetworkSimulation simulates how the packSocialNetwork.SocialNetwork works.
 * This project is being developed on Data Structures and Algorithms subject on UPV/EHU at 2020/2021 academic year.
 *
 * @author Iyan Alvarez and Davy Wellinger
 * @version firstMilestone
 */
public class SocialNetworkSimulation {

    public static void main(String[] args) {
        SocialNetwork sn = SocialNetwork.getInstance();
        sn.initialMenu();
    }
}
