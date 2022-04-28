import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    public ArrayList<String> links(String file) throws IOException {
        Path fileName = Path.of(file);
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        return links;
    }

    @Test
    public void getLinksTestFile() throws IOException {
        List<String> expected = List.of("https://something.com", "some-thing.html");

        assertEquals(expected, links("test-file.md"));
    }

    @Test
    public void getLinksOnlyBrackets() throws IOException {
        List<String> expected = List.of();

        assertEquals(expected, links("only-brackets.md"));
    }

    @Test
    public void getLinksOnlyParen() throws IOException {
        List<String> expected = List.of();

        assertEquals(expected, links("only-paren.md"));
    }

    @Test
    public void getLinksFarSyntax() throws IOException {
        List<String> expected = List.of();

        assertEquals(expected, links("far-syntax.md"));
    }

    @Test
    public void getLinksImageTest() throws IOException {
        List<String> expected = List.of();

        assertEquals(expected, links("image-test.md"));
    }
    
    @Test
    public void getLinksBreakingTest() throws IOException {
        List<String> expected = List.of("failure");

        assertEquals(expected, links("breaking-test.md"));
    }
}
