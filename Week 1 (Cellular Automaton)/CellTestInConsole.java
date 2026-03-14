import java.util.Arrays;
import java.util.ArrayList;

public class CellularAutomatonSimulator extends ConsoleProgram
{
    public void display(String comment)
    {
        //ANSI Color Codes
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[41m";
        String ANSI_GREEN = "\u001B[42m";
        
        String currentLine = "";
        
        System.out.println("-----" + comment + "-----");
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                if(Cell.cells.get(i*10 + j).state)
                {
                    currentLine += ANSI_GREEN + "[O]" + ANSI_RESET;
                }
                else
                {
                    currentLine += ANSI_RED + "[O]" + ANSI_RESET;
                }
            }
            
            System.out.println(currentLine);
            currentLine = "";
        }
        
        String series = "";
        for(int i = 0; i < comment.length(); i++)
        {
            series += "-";
        }
        System.out.println("-----" + series + "-----");
    }
    
    public void delay(int milliseconds)
    {
        try 
        {
            Thread.sleep(milliseconds);
        } 
        catch (InterruptedException e) 
        {
            Thread.currentThread().interrupt(); // Restore the interrupt flag
            System.err.println("Thread was interrupted: " + e.getMessage());
        }

    }
    
    public void run()
    {
        //ANSI Color Codes
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[41m";
        String ANSI_GREEN = "\u001B[42m";
        
        //Program
        System.out.println("Program Started!\n---------------------------------");
        
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                String name = "Cell" + i + j;
                Cell newCell = new Cell(name, i, j);
            }
        }
        
        display("Before Initialization");
        
        delay(1000);
        for(int i = 0; i < 100; i++)
        {
            if(i%5 == 0)
            {
                Cell.cells.get(i).setState(true);
            }
        }
        display("Start State");
        
        for(int i = 0; i < 15; i++)
        {
            delay(1000);
            Cell.runAllOnce();
            display("State " + (i + 1));
        }
        
        while(true)
        {
            String repeat = readLine("Run again? (y/n)");
            if(repeat.equals("n"))
            {
                break;
            }
            else
            {
                for(int i = 0; i < 15; i++)
                {
                    delay(1000);
                    Cell.runAllOnce();
                    display("State " + (i + 1));
                }
            }
        }
    }
}
