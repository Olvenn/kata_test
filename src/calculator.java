class Calculator {

    private final String sign;
    private final int  firstNumber;
    private final int secondNumber;

    public Calculator(String sign, int firstNumber, int secondNumber) {
        this.sign = sign;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public int  getResult() {
        int result = 0;
        switch (sign) {
            case "+" ->
                    result = firstNumber + secondNumber;
            case "-" ->
                    result = firstNumber - secondNumber;
            case "*" ->
                    result = firstNumber * secondNumber;
            case "/" ->
                    result = firstNumber / secondNumber;
            default -> System.out.println("ни одно из предыдущих условий не подошло");
        }

        return (int) result;
    }
}
