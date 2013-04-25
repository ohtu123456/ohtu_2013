import com.ohtu123456.ohtu_2013.Storage.*
import com.ohtu123456.ohtu_2013.UserInterface.*
import com.ohtu123456.ohtu_2013.logic.*
import com.ohtu123456.ohtu_2013.Storage.*

book =  ["-add",
        "-book",
        "BA04",
        "Beck, Kent and Andres, Cynthia",
        "Extreme Programming Explained: Embrace Change (2nd Edition)",
        "2004",
        "Addison-Wesley Professional"]

bookprint = "{ID: BA04, AUTHOR: Beck, Kent and Andres, Cynthia, TITLE: Extreme Programming Explained: Embrace Change (2nd Edition), YEAR: 2004}"

inproceedings = ["-add",
                 "-inproceedings",
                 "Begel_2008",
                 "Begel, Andrew and Simon, Beth",
                 "Struggles of new college graduates in their first software development job",
                 "2008",
                 "ACM",
                 "Proceedings of the SIGCSE '08",
                 "133"]

inproceedingsprint = "{ID: Begel_2008, AUTHOR: Begel, Andrew and Simon, Beth, TITLE: Struggles of new college graduates in their first software development job, YEAR: 2008}"

filterandprint = ["-filter ACM",
                  "-print",
                  "-quit"]

book.addAll(inproceedings);
book.addAll(filterandprint);

testinput = book as String[];

scenario "user adds two references and applies a filter",   {
    given "a new program instance", {
        stubio = new StubIO(testinput)
        db = new StorageDatabase("testidatabase", true);
        logic = new Logic(db);
        ui = new UI(stubio,logic);
    }
    when "user adds the references and applies a filter",   {
        ui.initialize();
    }
    
    and "the results are collected",    {
        output = stubio.getInput()
    }
    
    then "they are filtered correctly",  {
        ensure(output.contains(inproceedingsprint))
        and
        ensure(!(output.contains(bookprint)))
    }
}