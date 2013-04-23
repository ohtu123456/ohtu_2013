import com.ohtu123456.ohtu_2013.Storage.*
import com.ohtu123456.ohtu_2013.UserInterface.*
import com.ohtu123456.ohtu_2013.logic.*

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