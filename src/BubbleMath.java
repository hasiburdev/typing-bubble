import java.util.Random;

public class BubbleMath extends Bubble {
    @Override
    public void generateText() {

        generateBasicCalculation();
    }

    public void generateBasicCalculation() {
        Random random = new Random();
        int num1 = 0, num2 = 0, answer = 0;
        String operator = "";

        int operatorChoice = random.nextInt(4);
        switch (operatorChoice) {
            case 0:
                operator = "+";
                do {
                    num1 = random.nextInt(10); // Random number between 0 and 9
                    num2 = random.nextInt(10); // Random number between 0 and 9
                    answer = num1 + num2;
                } while (answer >= 10);
                break;
            case 1:
                operator = "-";
                do {
                    num1 = random.nextInt(10); // Random number between 0 and 9
                    num2 = random.nextInt(10); // Random number between 0 and 9
                    answer = num1 - num2;
                } while (answer < 0);
                break;
            case 2:
                operator = "*";
                do {
                    num1 = random.nextInt(10); // Random number between 0 and 9
                    num2 = random.nextInt(10); // Random number between 0 and 9
                    answer = num1 * num2;
                } while (answer >= 10);
                break;
            case 3:
                operator = "/";
                do {
                    num2 = random.nextInt(9) + 1; // Random number between 1 and 9 to avoid division by zero
                    num1 = num2 * random.nextInt(10); // Ensure num1 is a multiple of num2
                    answer = num1 / num2;
                } while (answer >= 10);
                break;
        }

        displayText = num1 + " " + operator + " " + num2;
        answerText = String.valueOf(answer);
    }
}
