package springwiki.demo;

public class SiteHelper {

    public static String encodeForUrl(String input) {
        return input.toLowerCase().replaceAll("[^a-z\\s]", "").replaceAll("\\s", "-");
    }

}
