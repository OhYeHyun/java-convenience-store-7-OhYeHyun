package store.service;

public class MembershipService {
    private static final double MEMBERSHIP_DISCOUNT = 0.3;
    private static final int MAXIMUM_AMOUNT = 8000;

    private MembershipService() {}

    public static int applyMemberShip(int paymentAmount) {
        long appliedAmount = Math.round(paymentAmount * MEMBERSHIP_DISCOUNT);
        if (isMaximum(appliedAmount)) {
            return MAXIMUM_AMOUNT;
        }
        return (int) appliedAmount;
    }

    private static boolean isMaximum(long appliedAmount) {
        return appliedAmount > MAXIMUM_AMOUNT;
    }
}
