package ifabsent;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class TwitterUser {
    private String name;

    TwitterUser() {

    }

    TwitterUser(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");

        Map<String, TwitterUser> mapUsers = new HashMap<>();

        String name = "user";

        mapUsers.computeIfAbsent(name, new Function<String, TwitterUser>() {

			@Override
			public TwitterUser apply(String t) {
				return new TwitterUser(t);
			}
            
        });

        name = "user1";

        mapUsers.computeIfAbsent("user1", TwitterUser::new);

        printMap(mapUsers);

        mapUsers.values().stream().sorted(Comparator.comparing(TwitterUser::getName).reversed());

        printMap(mapUsers);

    }

    private static void printMap(Map<String, TwitterUser> mapUsers) {
        mapUsers.forEach((k, v) -> {
            System.out.println(String.format("%s: %s", k, v.getName()));
        });
    }
}