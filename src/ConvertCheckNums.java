public class ConvertCheckNums {

    static String[] arithmOperations = {"+", "-", "*", "/"};
    static String[] splitS = {"\\+", "-", "\\*", "/"};
    static int[] arabValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};
    static String[] romanLiterals = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    protected static int toArabic(String s) throws Exception {
        int arabNum = 0;
        int currentNum = 0;
        int nextCurrentNum = 0;
        char letter;
        for (int i = 0; i < s.length(); i++) {
            letter = s.charAt(i);
            currentNum = letterToNumber(letter);
            if (currentNum == -1) throw  new Exception("Некорректный ввод римского числа");
            if (i == s.length() - 1) {
                arabNum += currentNum;
            } else {
                letter = s.charAt(i + 1);
                nextCurrentNum = letterToNumber(letter);
                if (nextCurrentNum > currentNum) {
                    arabNum += (nextCurrentNum - currentNum);
                    i++;
                } else {
                    arabNum += currentNum;
                }
            }
        }
        return arabNum;
    }

    protected static String toRoman(int x) {
        StringBuilder romanNum = new StringBuilder();
        for (int i = 0; i < arabValues.length; i++) {
            while (x >= arabValues[i]) {
                x -= arabValues[i];
                romanNum.append(romanLiterals[i]);
            }
        }
        return romanNum.toString();
    }

    protected static int checkOperations(String s) throws Exception {
        int indexOfArithmOperation = -1;
        int countArithmOperations = 0;
        for (int i = 0; i < arithmOperations.length; i++) {
            if (s.contains(arithmOperations[i])) {
                countArithmOperations++;
                indexOfArithmOperation = i;
            }
        }
        if (countArithmOperations != 1) {
            throw new Exception("Неподдерживаемое число арифметических операций");
        } else {
            String[] checkOneSameOperation = s.split(splitS[indexOfArithmOperation]);
            if (checkOneSameOperation.length == 2) {
                return indexOfArithmOperation;
            } else throw new Exception("Неподдерживаемое число арифметических операций");
        }
    }

    protected static boolean checkNums(String first, String second) throws Exception {
        int count = 0;
        int x, y;

        try {
            x = toArabic(first);
        } catch (Exception exc) {
            count++;
            x = Integer.parseInt(first);
        }

        try {
            y = toArabic(second);
        } catch (Exception exc) {
            count++;
            y = Integer.parseInt(second);
        }

        if (count == 1) {
            throw new Exception("Введите числа корректного формата");
        } else if (x < 1 || x > 10 || y < 1 || y > 10) {
            throw new Exception("Введите числа от 1 до 10 включительно");
        } else {
            if (count == 0) {
                return true;
            } else return false;
        }
    }

    protected static int calculate (int x, int y, String operation) {
        switch (operation) {
            case "+" : return x + y;
            case "-" : return x - y;
            case "*" : return x * y;
            default:   return x / y;
        }
    }

    private static int letterToNumber(char letter) {
        switch (letter) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            default: return -1;
        }
    }
}
