 import java.util.HashMap;

public class Room
{
//Attributs
    private String aDescription;
    private HashMap<String, Room> aExits;
// Constructeur naturel
public Room(final String pDescription)
{
    this.aDescription=pDescription;
    aExits= new HashMap<String, Room>(); 
}
//Accesseur
public String getDescription() //Accède à la description de la pièce.
{
    return this.aDescription;
}

public Room getExit(final String pDirection)
 //Retourne les sorties de la pièce en paramètre
{
    return aExits.get(pDirection);
}
//Modificateur
public void setExits(final String pDirection, final Room pNeighbour) 
{
    aExits.put(pDirection, pNeighbour);
}
//Méthodes
public StringBuilder getExitString() //Donne les sorties sous forme de String
{
    StringBuilder vExits=new StringBuilder();
    for (HashMap.Entry<String, Room> entry: aExits.entrySet())
    {
        if (entry.getValue()!=null) 
        {
            vExits=vExits.append(entry.getKey()+" ");
        }
    }
    
    return vExits;
}

public String getLongDescription()
{
    return "You are " + this.getDescription() + ".\n" + "Exits: " + getExitString();
}

} // Room
