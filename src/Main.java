import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        // Creating 22 Million user objects in allUsers collection
        List<User> allUsers = Data.getAllUsers();
        for (int i = 0; i < 10000; i++) {
            allUsers.addAll(Data2.getAllOtherUsers());
        }

        /* // If you want to try Stream API then make comment for "for" loop example.
        // Using the Stream API map method
        long startStream = System.currentTimeMillis();
        List<UserResponse> userResponses = allUsers.stream().filter(user -> user.getDateOfBirth().isAfter( LocalDate.parse("1990-01-01"))).map(user -> new UserResponse.Builder()
                                                                                                .withId(user.getId())
                                                                                                .withFirstName(user.getFirstName())
                                                                                                .withLastName(user.getLastName())
                                                                                        .build())
                                                                            .collect(Collectors.toList());
        long endStream = System.currentTimeMillis();
        long durationStream = endStream - startStream;
        System.out.println("Stream API map execution time: " + durationStream + " ms");
        */

        long startLoop = System.currentTimeMillis();
        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : allUsers ) {
            if (user.getDateOfBirth().isAfter(LocalDate.parse("1900-01-01"))) {
                UserResponse userResponse = new UserResponse.Builder()
                        .withId(user.getId())
                        .withFirstName(user.getFirstName())
                        .withLastName(user.getLastName())
                        .build();
                userResponses.add(userResponse);
            }
        }
        long endLoop = System.currentTimeMillis();
        long durationLoop = endLoop - startLoop;
        System.out.println("For-loop execution time: " + durationLoop + " ms");


    }
}