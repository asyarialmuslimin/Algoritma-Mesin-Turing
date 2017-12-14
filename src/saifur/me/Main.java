package saifur.me;

import javax.swing.*;
import java.lang.String;

public class Main {
    static int head;
    static String state;
    static String pita[] = new String[9];
    static boolean accepted, rejected;
    static String input;
    static final String s = "A";
    static final String sigma = "01XYB";

    static String indicator = "^";

    static void inputan() {
        boolean invalid;
        String Spita;
        do {
            invalid = false;
            Spita = JOptionPane.showInputDialog("Masukkan String Pita");
            if (Spita.length() > 9) {
                JOptionPane.showMessageDialog(null, "Panjang pita tidak boleh lebih dari 9");
                invalid = true;
            }
        } while (invalid);

        for (int i = 0; i < Spita.length(); i++) {
            pita[i] = Spita.substring(i, i + 1);
        }
    }

    static void validasi() {
        boolean valid = false;
        int cek;
        try {
            for (int k = 0; k < pita.length; k++) {
                cek = sigma.indexOf(pita[k]);
                if (cek > 0) {
                    valid = true;
                } else if (cek < 0) {
                    valid = false;
                }
            }

        } catch (Exception e) {

        }
        if (valid) {
            JOptionPane.showMessageDialog(null, "Inputan Anda Valid");
        } else if (!valid) {
            JOptionPane.showMessageDialog(null, "Inputan Anda Invalid");
            System.exit(0);
        }
    }

    static void turing() {
        head = 0;
        accepted = false;
        rejected = false;
        state = s;

        while (!accepted || !rejected) {
            input = pita[head];
            if (input == null)
                input = "B";
            JOptionPane.showMessageDialog(null, "Memproses State Ke " + state + input);
            tabelTransisi(state, input);
        }
    }

    static void tabelTransisi(String stateKini, String input) {
        switch (stateKini) {
            case "A":
                switch (input) {
                    case "0":
                        transisi("B", "X", "R");
                        break;
                    case "Y":
                        transisi("D", "Y", "R");
                        break;
                }
                break;
            case "B":
                switch (input) {
                    case "0":
                        transisi("B", "0", "R");
                        break;
                    case "1":
                        transisi("C", "Y", "L");
                        break;
                    case "Y":
                        transisi("B", "Y", "R");
                        break;
                }
                break;
            case "C":
                switch (input) {
                    case "0":
                        transisi("C", "0", "L");
                        break;
                    case "X":
                        transisi("A", "X", "R");
                        break;
                    case "Y":
                        transisi("C", "Y", "L");
                        break;
                }
                break;
            case "D":
                switch (input) {
                    case "0":
                        transisi("D", "E", "");
                        break;
                    case "1":
                        transisi("D", "E", "");
                        break;
                    case "Y":
                        transisi("D", "Y", "R");
                        break;
                    case "B":
                        JOptionPane.showMessageDialog(null, "String Dikenali oleh Mesin Turing");
                        accepted = true;
                        System.exit(0);
                        break;
                    case "E":
                        JOptionPane.showMessageDialog(null, "String tidak Dikenali oleh Mesin Turing");
                        rejected = true;
                        System.exit(0);
                        break;
                }
                break;
        }

    }

    static void transisi(String stateBaru, String output, String geser) {
        JOptionPane.showMessageDialog(null, "Proses Transisi(" + stateBaru + "," + output + "," + geser + ")");
        pita[head] = output;
        if (geser == "L") {
            head--;
            indicator = indicator.substring(3, indicator.length());
        } else if (geser == "R") {
            head++;
            indicator = "   " + indicator;
        }
        state = stateBaru;
        tampilPita();
    }

    static void tampilPita() {
        String Spita = "";

        for (int i = 0; i < pita.length; i++) {
            if (pita[i] == null) {
                Spita = Spita + "B ";
            } else {
                Spita = Spita + pita[i] + " ";
            }
        }
        JOptionPane.showMessageDialog(null, "Isi Pita Adalah :\n" + Spita + "\n" + indicator);
    }

    public static void main(String[] args) {

        inputan();
        tampilPita();
        validasi();
        turing();

    }
}

