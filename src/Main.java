import java.util.*;

public class Main {
    public static void main(String[] args) {
        String [] operations = {"+", "-", "/", "*"};
        String patternRoman = "[IVX]*";
        String patternNumber = "[0-9]*";
        String operation = "";
        int  programResult = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Введите через пробелы число от 1 до 10; один из знаков + - / *; и второе число от 1 до 10.");
        String string = scanner.nextLine();
        String[] userInput = string.split(" ");
        operation = "";
        String firstNumber;
        String secondNumber;

        // Проверяем ввод на корректность введения числа + знака + числа
        if (userInput.length != 3) {
            System.out.println("Не корректный ввод.");
            return;
        } else {
            firstNumber = userInput[0];
            secondNumber = userInput[2];
        }

        // Проверяем корректность ввода оператора
        List<String> operationsList = new ArrayList<>(Arrays.asList(operations));
        if (operationsList.contains(userInput[1])) {
            operation = userInput[1];
        } else {
            System.out.println("Не корректный арифметический оператор.");
            return;
        }

        if (firstNumber.matches(patternNumber) && secondNumber.matches(patternNumber)){
            int number1 = Integer.parseInt(firstNumber);
            int number2 = Integer.parseInt(secondNumber);
            if (number1 < 11 && number1 > 0 && number2 < 11 && number2 > 0) {
                Calculator calculation = new Calculator(operation, number1, number2);
                programResult = calculation.getResult();
                System.out.println("Результат равен " + programResult);
            } else {
                System.out.println("Числа должны быть от 1 до 10");
            }
        } else if (firstNumber.matches(patternRoman) && userInput[2].matches(patternRoman)){
            if (isPresentRoman(firstNumber) && isPresentRoman(secondNumber)){
                Roman roman1 = Roman.valueOf(firstNumber);
                Roman roman2 = Roman.valueOf(secondNumber);
                int number1 = roman1.getTranslation();
                int number2 = roman2.getTranslation();
                Calculator calculation = new Calculator(operation, number1, number2);
                programResult = calculation.getResult();
                if (programResult < 1) {
                    System.out.println("В римской системе нет отрицательных чисел.");
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
                System.out.println("Введены не корректные Римские числа. Можно вводить числа от I до X.");
            }
        } else {
            System.out.println("Введены некорректные числа.");
        }
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

