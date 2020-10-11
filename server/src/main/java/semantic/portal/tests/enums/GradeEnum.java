package semantic.portal.tests.enums;

public enum GradeEnum {
    A, B, C, D, E, F;

    public static GradeEnum getByValue(long mark) {
        if (mark >= 95) return A;
        else if (mark >= 85) return B;
        else if (mark >= 75) return C;
        else if (mark >= 65) return D;
        else if (mark >= 60) return E;
        else return F;
    }
}
