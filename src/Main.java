import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Введите через пробелы число от 1(I) до 10(X); один из знаков + - / *; и второе число от 1(I) до 10(X).");
        String string = scanner.nextLine();

        calc(string);
    }

    public static int calculate(String sign, int firstNumber, int secondNumber) {
        int result = 0;
        switch (sign) {
            case "+" ->
                    result = firstNumber + secondNumber;
            case "-" ->
                    result = firstNumber - secondNumber;
            case "*" ->
                    result = firstNumber * secondNumber;
            case "/" -> {
                try {
                    result = firstNumber / secondNumber;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");
                    break;
                }
            }
            default -> System.out.println("ни одно из предыдущих условий не подошло");
        }

        return (int) result;
    }

    public static String calc(String input) {
        String [] operations = {"+", "-", "/", "*"};
        String patternRoman = "[IVX]*";
        String patternNumber = "[0-9]*";
        List<String> operationsList = new ArrayList<>(Arrays.asList(operations));

        String operation = "";
        int programResult = 0;

        String[] userInput = input.split(" ");
        operation = "";

        // Проверяем ввод на корректность введения числа + знака + числа
        if (userInput.length != 3) {
            throw new RuntimeException("Неверный формат ввода");
        }

        String firstNumber = userInput[0];
        String secondNumber = userInput[2];
        // Проверяем корректность ввода оператора
        if (operationsList.contains(userInput[1])) {
            operation = userInput[1];
        } else {
            throw new RuntimeException("Не корректный арифметический оператор.");
        }

        if (firstNumber.matches(patternNumber) && secondNumber.matches(patternNumber)){
            int number1 = Integer.parseInt(firstNumber);
            int number2 = Integer.parseInt(secondNumber);
            if (number1 < 11 && number1 > 0 && number2 < 11 && number2 > 0) {
                programResult = calculate(operation, number1, number2);
                System.out.println("Результат равен " + programResult);
            } else {
                throw new RuntimeException("Числа должны быть от 1 до 10");
            }
        } else if (firstNumber.matches(patternRoman) && userInput[2].matches(patternRoman)){
            if (isPresentRoman(firstNumber) && isPresentRoman(secondNumber)){
                Roman roman1 = Roman.valueOf(firstNumber);
                Roman roman2 = Roman.valueOf(secondNumber);
                int number1 = roman1.getTranslation();
                int number2 = roman2.getTranslation();
                programResult = calculate(operation, number1, number2);
                if (programResult < 1) {
                    throw new RuntimeException("В римской системе только положительные числа.");
                } else {
                    String romanResult = "N_" + programResult;
                    if (isPresentArabian(romanResult)) {
                        Arabian resulsArabian = Arabian.valueOf(romanResult);
                        System.out.println("Результат  " + resulsArabian.getTranslation());
                    } else {
                        System.out.println("Результат  " + convertToRoman(programResult));
                    }
                }
            } else {
                throw new RuntimeException("Введены не корректные Римские числа. Можно вводить числа от I до X.");
            }
        } else {
            throw new RuntimeException("Введены некорректные числа, либо из разных систем счисления.");
        }
        return  Integer.toString(programResult);
    }
    public static boolean isPresentRoman(String inputNum){
        try {
            Roman.valueOf(inputNum);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isPresentArabian(String inputNum){
        try {
            Arabian.valueOf(inputNum);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String convertToRoman(int num) {
        int units = num % 10;
        String unitsStr = "N_" + units;
        Arabian unitsStrArabian = Arabian.valueOf(unitsStr);

        int tens = num - units;
        String tensStr = "N_" + tens;
        Arabian tensStrArabian = Arabian.valueOf(tensStr);

        return  tensStrArabian.getTranslation() + unitsStrArabian.getTranslation();
    }
}

