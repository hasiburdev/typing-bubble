
public class BubbleWord extends Bubble {
    @Override
    public void generateText() {

        String[] words = {
                "cat", "dog", "sun", "pen", "box", "hat", "car", "man", "fan", "toy",
                "bat", "run", "leg", "arm", "sky", "bed", "ant", "lip", "fog", "bus",
                "cup", "cap", "map", "net", "pig", "pan", "zip", "fox", "log", "cow",
                "bee", "jar", "kit", "rod", "jet", "owl", "rug", "wax", "tap", "nut",
                "fig", "jam", "hop", "web", "pop", "rat", "mud", "gem", "van", "pad",
                "wig", "hen", "pot", "bib", "rib", "cub", "lid", "dot", "bay", "gum",
                "nap", "pub", "oak", "rip", "dab", "tin", "bay", "fan", "lip", "pit",
                "dig", "ham", "hip", "kit", "log", "pet", "bet", "bed", "big", "hit",
                "fit", "tie", "row", "owl", "act", "bug", "boy", "toy", "box", "mop",
                "can", "tip", "sit", "win", "nut", "zip", "bat", "pot", "jet", "cap",
                "bow", "fun", "red", "bug", "pat", "tap", "top", "fin", "sun", "cup",
                "bag", "tan", "wet", "mug", "vet", "jam", "wig", "bun", "joy", "pad",
                "egg", "gym", "mix", "tax", "elf", "row", "bat", "gas", "fig", "tan",
                "mud", "mob", "kit", "leg", "mud", "run", "gum", "kit", "pet", "mat",
                "mud", "top", "mop", "cow", "dog", "bug", "pot", "bib", "dot", "sun",
                "man", "pan", "pit", "pat", "bet", "dig", "fan", "log", "cat", "cut",
                "bat", "bag", "run", "man", "lid", "van", "wet", "pot", "dog", "pen",
                "cat", "sun", "fun", "jet", "tip", "gym", "mix", "nut", "pen", "mud",
                "jar", "box", "row", "dot", "lid", "pen", "pad", "run", "bus", "ant",
                "cup", "map", "leg", "bat", "mat", "log", "rat", "zip", "hit", "boy",
                "pet", "man", "fan", "top", "cut", "mop", "bat", "jet", "fig", "pat",
                "egg", "mix", "kit", "gas", "pan", "sun", "fun", "tip", "dot", "nut",
                "bat", "pen", "pad", "mop", "cat", "bat", "cup", "pan", "dog", "sun",
                "man", "pot", "cow", "log", "fan", "sun", "cup", "pen", "map", "dog",
                "box", "cat", "bat", "fun", "wet", "pot", "dot", "top", "bat", "mug",
                "joy", "pad", "mop", "cow", "dog", "bug", "pot", "bib", "dot", "sun",
                "man", "pan", "pit", "pat", "bet", "dig", "fan", "log", "cat", "cut",
                "bow", "fun", "red", "bug", "pat", "tap", "top", "fin", "sun", "cup",
                "bag", "tan", "wet", "mug", "vet", "jam", "wig", "bun", "joy", "pad",
                "egg", "gym", "mix", "tax", "elf", "row", "bat", "gas", "fig", "tan",
                "mud", "mob", "kit", "leg", "mud", "run", "gum", "kit", "pet", "mat",
                "mud", "top", "mop", "cow", "dog", "bug", "pot", "bib", "dot", "sun"
        };

        int randomIndex = random.nextInt(words.length);

        String randomWord = words[randomIndex];

        answerText = randomWord;
        displayText = answerText;
    }

}
