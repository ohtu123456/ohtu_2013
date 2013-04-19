scenario "user adds a new reference and prints it", {
    given "a new program instance", {
        ui = new UI(new Logic());
        ui.initialize();
        ui.setOutput("testfile.txt");
    }
    
    when "selects save",    {
        ui.setInput("-save\n-quit\n");
    }
    
    then "all references are saved",    {
        ui.start();
        testOutput = new File("testfile.txt").text;
        ensure(testOutput)  {
            contains("saved");
        }
    }
}