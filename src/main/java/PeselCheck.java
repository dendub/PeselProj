public class PeselCheck {


    public static boolean checking( String pesel) {
        int[] convertPesel = new int[pesel.length()];
        for (int i = 0; i < pesel.length(); i++) {
            convertPesel[i] = Character.digit(pesel.charAt(i), 10);
        }
        if (convertPesel.length != 11) {
            System.out.println("Pesel too short");
            return false;
        } else {
            int check = 0;
            check = 1 * convertPesel[0] + 3 * convertPesel[1] + 7 * convertPesel[2] + 9 * convertPesel[3] + 1 * convertPesel[4] + 3 * convertPesel[5] + 7 * convertPesel[6] + 9 * convertPesel[7] + 1 * convertPesel[8] + 3 * convertPesel[9];
            check = check % 10;
            check = 10 - check;
            if (check != convertPesel[10]) {
                    System.out.println("Pesel incorrect");
                    return false;
            }else{
                    System.out.println("Good");
                    return true;
            }
        }
    }
}