import beans.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        List<Person> people = readFromFile("people.txt");
        printToJSON(people);
    }

    @SneakyThrows
    private void printToJSON(List<Person> people) {
        ObjectMapper mapper = new ObjectMapper();
        String string = mapper.writeValueAsString(people);
        System.out.println(string);
        List<Person> persons = mapper.readValue(string, new TypeReference<List<Person>>() {
        });
    }

    @SneakyThrows
    private List<Person> readFromFile(String file) {
        return Files.newBufferedReader(Paths.get(file)).lines().map(Person::parsePerson).collect(Collectors.toList());
    }
}
