package se.jakobkrantz.magicmirror.util;
/*
 * Created by krantz on 16-01-05.
 */

public class Greetings {

    public static String[] morning() {
        String[] greetings = {
                "Good morning!",
                "Coffee?",
                "Have a nice day!",
                "Good morning, handsome!",
                "Enjoy your day!",
                "How was your sleep?"
        };
        return greetings;
    }

    public static String[] afternoon() {
        String[] greetings = {
                "Looking good today!",
                "Good afternoon!"
        };
        return greetings;
    }

    public static String[] evening() {
        String[] greetings = {
                "Good evening!",
                "Wow, you look hot!",
                "You look nice!"
        };
        return greetings;
    }
}
