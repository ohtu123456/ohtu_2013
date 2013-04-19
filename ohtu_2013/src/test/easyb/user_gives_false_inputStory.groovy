scenario "user gives false input"   {
    
    given "a new program instance", {
        ui = new UI(new Logic());
        ui.initialize();
        ui.setOutput("testfile.txt");
    }
    
    when "false input is given",    {
        ui.setInput("-adds\n-quit\n");
        ui.start();
    }
    
    then "an exception is thrown",  {
        testOutput = new File("testfile.txt").text;
        ensure(testOutput)  {
            contains("exception");
        }
    }
}