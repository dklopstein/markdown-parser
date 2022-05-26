import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// commands to run MarkdownParseTest
// javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar MarkdownParseTest.java  
// java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore MarkdownParseTest

public class MarkdownParseTest {
    //@Test
    public void testSnippet1() throws IOException {
      List<String> expected = List.of("`google.com", "google.com","ucsd.edu");
      Path fileName = Path.of("snippet-1.md");
      String content = Files.readString(fileName);
      assertEquals(expected, MarkdownParse.getLinks(content)); 
    }

    @Test
    public void testSnippet2() throws IOException {
      List<String> expected = List.of("a.com", "a.com(())", "example.com");
      Path fileName = Path.of("snippet-2.md");
      String content = Files.readString(fileName);
      assertEquals(expected, MarkdownParse.getLinks(content)); 
    }

    //@Test
    public void testSnippet3() throws IOException {
      List<String> expected = List.of("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
      Path fileName = Path.of("snippet-3.md");
      String content = Files.readString(fileName);
      assertEquals(expected, MarkdownParse.getLinks(content)); 
    }
}
