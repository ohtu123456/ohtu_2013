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
        "Addison-Wesley Professional",
        "-print",
        "-quit"] as String[]

//bookprint = "{ID: BA04,  AUTHOR: Beck, Kent and Andres, Cynthia,  TITLE: Extreme Programming Explained: Embrace Change (2nd Edition),  YEAR: 2004}"
bookprint = "@book{BA04,author = {Beck, Kent and Andres, Cynthia},title = {Extreme Programming Explained: Embrace Change (2nd Edition)},year = {2004},publisher = {Addison-Wesley Professional}"
inproceedings = ["-add",
                 "-inproceedings",
                 "Begel_2008",
                 "Begel, Andrew and Simon, Beth",
                 "Struggles of new college graduates in their first software development job",
                 "2008",
                 "ACM",
                 "Proceedings of the SIGCSE '08",
                 "133",
                 "-print",
                 "-quit"] as String[]

//inproceedingsprint = "{ID: Begel_2008,  AUTHOR: Begel, Andrew and Simon, Beth,  TITLE: Struggles of new college graduates in their first software development job,  YEAR: 2008}"
inproceedingsprint = "@inproceedings{Begel_2008,author = {Begel, Andrew and Simon, Beth},title = {Struggles of new college graduates in their first software development job},address = {},pages = {133},year = {2008},booktitle = {Proceedings of the SIGCSE '08},publisher = {ACM},}"

filterandprint = ["-filter ACM",
                  "-print",
                  "-quit"] as String[]

bookAndInproceedings = (book << inproceedings).flatten();
testInput = (bookAndInproceedings << filterandprint).flatten();

scenario "user adds two references and applies a filter",   {
    given "a new program instance", {
        stubio = new StubIO(book)
        db = new StorageDatabase("testidatabase", true);
        logic = new Logic(db);
        ui = new UI(stubio,logic);
    }
    when "user adds the references and applies a filter",   {
        ui.initialize();
    }
    then "the results are filtered correctly",  {
        output = iostub.getInput();
        output.shouldHave inproceedingsprint
        and
        output.shouldNotHave bookprint
    }
}