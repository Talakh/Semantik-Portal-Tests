package semantic.portal.tests.enums;

public enum GradeEnum {
    A(true),
    B(true),
    C(true),
    D(true),
    E(true),
    F(false);

    private boolean isPassed;

    GradeEnum(boolean isPassed) {
        this.isPassed = isPassed;
    }

    public static GradeEnum getByValue(long mark) {
        if (mark >= 95) return A;
        else if (mark >= 85) return B;
        else if (mark >= 75) return C;
        else if (mark >= 65) return D;
        else if (mark >= 60) return E;
        else return F;
    }

    public boolean isPassed() {
        return isPassed;
    }
}
