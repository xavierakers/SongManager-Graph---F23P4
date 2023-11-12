import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

public class MainTest extends TestCase {
    public void testMain() {
        String[] args = new String[] { "8", "testInput.txt" };
        GraphProject.main(args);
    }

// public void testDuplicateInsert() throws IOException {
// String[] args = new String[] { "8",
// "testInputFiles/testDuplicateInsert.txt" };
// GraphProject.main(args);
// String expected = new String(Files.readAllBytes(Paths.get(
// "testOutputFiles/testDuplicateInsert_output.txt")));
//
// assertEquals(expected, systemOut().getHistory());
// }


    public void testDuplicateArtist() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testDuplicateArtist.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testDuplicateArtist_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    public void testInsertSimple() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testInsertSimple.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testInsertSimple_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }


    public void testRemoveSimple() throws IOException {
        String[] args = new String[] { "8",
            "testInputFiles/testRemoveSimple.txt" };
        GraphProject.main(args);
        String expected = new String(Files.readAllBytes(Paths.get(
            "testOutputFiles/testRemoveSimple_output.txt")));

        assertEquals(expected, systemOut().getHistory());
    }
// public void testInsertComplex() throws IOException{
// String[] args = new String[] { "16",
// "testInputFiles/testInsertComplex.txt" };
// GraphProject.main(args);
// String expected = new String(Files.readAllBytes(Paths.get(
// "testOutputFiles/testInsertComplex_output.txt")));
//
// assertEquals(expected, systemOut().getHistory());
// }

}
