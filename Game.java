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
        Room vGrocery=new Room("in the grocery");
        Room vHome=new Room("at home");
        Room vGym1=new Room("in the first floor of the gym");
        Room vLockers=new Room("in the lockers of the gym");
        Room vGym0=new Room("in the basement of the gym");
        Room vRestaurant=new Room("in the Restaurant");
        Room vToilets=new Room("in the toilets of the restaurant");
        //Sorties
        vOutside.setExits("south",vGym1);
        vOutside.setExits("north",vRestaurant);
        vOutside.setExits("east",vHome);
        vOutside.setExits("west",vGrocery);
        vGrocery.setExits("east",vOutside);
        vHome.setExits("west",vOutside);
        vGym1.setExits("down",vGym0);
        vGym1.setExits("north",vOutside);
        vGym1.setExits("east",vLockers);
        vLockers.setExits("west",vGym1);
        vGym0.setExits("up",vGym1);
        vRestaurant.setExits("south",vOutside);
        vRestaurant.setExits("down",vToilets);
        vToilets.setExits("up",vRestaurant);
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
        System.out.println("Welcome to Bodybuilding Adventure ! ");
        System.out.println("Bodybuilding is a new, incredible game.");
        System.out.println("Type 'help' if you need help.");
        this.printLocationInfo();
    }
  
    
    private void printHelp()
    {
        System.out.println("You are lost. You are alone.");
        System.out.println("You wander in the street");
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
