
public class BubbleLetter extends Bubble {

    @Override
    public void generateText() {
        String[] lettersAndNumbers = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".split("");
        answerText = lettersAndNumbers[random.nextInt(lettersAndNumbers.length)];
        displayText = answerText;
    }

}
