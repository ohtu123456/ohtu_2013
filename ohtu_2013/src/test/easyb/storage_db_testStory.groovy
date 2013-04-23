import com.ohtu123456.ohtu_2013.Storage.*
import com.ohtu123456.ohtu_2013.UserInterface.*
import com.ohtu123456.ohtu_2013.logic.*

scenario "test",    {
    given "a database", {
        db = new StorageDatabase("testidb");
    }

    when "adding an article", {
        map = new HashMap<String, String>();
        map.put("author", "pekka");
        db.addReference("article", map);
    }

    then "it should not be empty",   {
        db.getReferences().size.shouldNotBe 0
    }
} 


scenario "test",    {
    given "a new program instance", {
        userInput = "-add testidatabase -book a b c d c -quit".split();
        stubio = new StubIO(userInput)
        logic = new Logic();
        ui = new UI(stubio,logic);
    }

    when "user adds a new book reference", {
        ui.initialize();
    }

    then "a new reference is added",   {
        output = stubio.getInput();
        ensure(output)  {
            contains("New reference added");
        }
    }
} 