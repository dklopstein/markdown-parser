//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            //System.out.println(currentIndex);
            // fixed empty lines and non-links 
            if (markdown.indexOf(")", currentIndex) == -1 ||
                markdown.indexOf("]", currentIndex) == -1 ||
                markdown.indexOf("(", currentIndex) == -1 ||
                markdown.indexOf("[", currentIndex) == -1) { 
                break;
            }
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = markdown.indexOf(")", openParen);
            // fixed image being read as a link
            // fixed distance between [] and ()
            //System.out.println(openBracket);
            //System.out.println(markdown.substring(openParen + 1, closeParen));
            if ((markdown.indexOf("!", currentIndex) == openBracket - 1 &&
                 openBracket != 0)) {
                currentIndex = closeParen;
                continue;
            }

            // fixing inline backticks
            // if (markdown.indexOf("`", currentIndex) == openBracket - 1 ||
            //     markdown.indexOf("`", closeBracket) == closeBracket + 1) {
            //     for (int i = currentIndex + 1; i < closeBracket; i++) {
            //         if (markdown.charAt(i) == '`') {
            //             currentIndex = closeParen;
            //             break;
            //         }
            //     }
            //     continue;
            // }

            // fixing nested parenthesis
            while (markdown.charAt(closeParen + 1) == ')') {
                closeParen++;
            }
            
            // fix line breaks
            // String newline = System.getProperty("line.separator");

            // if (markdown.substring(openBracket, closeBracket).contains(newline) ||
            //     markdown.substring(openParen, closeParen).contains(newline)) {
            //     currentIndex = closeParen;
            //     continue;
            // }
            

            // making a change on remote server 
            //System.out.println("adding");
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1; 
            
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        //System.out.println("Here are the links found in " + args[0] + ":");
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
