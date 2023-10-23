import org.springframework.stereotype.Service;
import com.osumed.chatapplication.domain.Person;
import java.util.HashMap;

@Service
public class PersonService {
	
    // Declare a HashMap with Long keys and Person values
    private HashMap<Long, Person> personMap = new HashMap<>();

    public Person getPerson(Long userId) {
        // Retrieve a person from the map by user ID
        return personMap.get(userId);
    }
}
