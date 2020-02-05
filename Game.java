public class Game
{
    //Attribut
    private Room aCurrentRoom;
    private Parser aParser;
    //Procédure
    private void createRooms()
    {
        //Initialisation des pièces
        Room vOutside=new Room("in the street, outside the gym");
        Room vGym=new Room("in a lecture theatre");
        Room vRestaurant=new Room("in the Restaurant");
        //Sorties
        vOutside.setExits("south",vGym);
        vOutside.setExits("north",vRestaurant);
        //Lieu courant
        this.aCurrentRoom=vOutside;
    }
    
    private void printLocationInfo()
    {
     this.look();
    }
    //Constructeur par Défaut
    public Game()
    {
        createRooms();
        this.aParser=new Parser();
    }
    //Procédure
    private void goRoom(final Command pCommand) 
    {
           if (!pCommand.hasSecondWord()) //Si commande "go" sans la direction
        {
            System.out.println("Go where ?");
            return;
        }
            Room vNextRoom=null;
            String vDirection=pCommand.getSecondWord();
        if(vDirection.equals("north") ||vDirection.equals("east") 
        || vDirection.equals("west") || vDirection.equals("south") || vDirection.equals("down")
        || vDirection.equals("up"))
        //"if" vérifiant 
        {
            vNextRoom=this.aCurrentRoom.getExit(vDirection);
        }
            else 
            {
                System.out.println("Unknown direction !!!!!!");
                return;
            }
        
            if (vNextRoom==null) {
                System.out.println("There is no door !");
        }
        
        else 
        {
            this.aCurrentRoom=vNextRoom;
            this.printLocationInfo();
        }
    }
    
    private void printWelcome() 
    {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        this.printLocationInfo();
    }
  
    
    private void printHelp()
    {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander around at the university.");
        System.out.println("Your command words are: ");
        System.out.println(CommandWords.getStringValidCommands());
        
    }
    
    public void play()
    {
        this.printWelcome();
        boolean vFinished=false;
        
        while (vFinished==false)
        {
            Command vC=this.aParser.getCommand();
            vFinished=this.processCommand(vC);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    //Fonctions
    private boolean quit(final Command pC1)
    {
         
         if (pC1.hasSecondWord()==true)
         {
             System.out.println("Quit what ?");
             return false;
            }
         else 
         {
             return true;
            }
    }
    
    private boolean processCommand(final Command pC2)
    {
        if (pC2.isUnknown()==true)
        {
            System.out.println("I don't know what you mean...");
            return false;
        }
        else 
        {
            if (pC2.getCommandWord().equals("quit")){
                return quit(pC2);
            }
            
            if (pC2.getCommandWord().equals("help")){
                printHelp();
            }
            
            if (pC2.getCommandWord().equals("go")) 
            {
                goRoom(pC2);
            }
            
            if (pC2.getCommandWord().equals("look"))
            {
                look();
            }
            
            if (pC2.getCommandWord().equals("eat"))
            {
                System.out.println("You have eaten now and you are not hungry any more." + "\n" + "You have gained 2 kilograms.");
            }
                return false;
 
        }
        
    }
    
    private void look() 
    {
        System.out.println(aCurrentRoom.getLongDescription());
    }
} // Game
