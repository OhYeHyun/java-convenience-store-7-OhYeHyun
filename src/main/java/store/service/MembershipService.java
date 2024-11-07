package store.service;

public class MembershipService {
    private static final double MEMBERSHIP_DISCOUNT = 0.3;
    private static final int MAXIMUM_AMOUNT = 8000;

    public MembershipService() {

    }

    static int applyMemberShip(int paymentAmount) {
        long appliedAmount = Math.round(paymentAmount * (1 - MEMBERSHIP_DISCOUNT));
        if (isMaximum(appliedAmount)) {
            return MAXIMUM_AMOUNT;
        }
        return (int) appliedAmount;
    }

    static boolean isMaximum(long appliedAmount) {
        return appliedAmount > MAXIMUM_AMOUNT;
    }
}
