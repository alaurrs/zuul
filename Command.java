 

public class Command
{
 //Attributs
    private String aCommandWord;
    private String aSecondWord;
 //Constructeur Naturel
    public Command(final String pCommandWord, final String pSecondWord) 
    {
        this.aCommandWord=pCommandWord;
        this.aSecondWord=pSecondWord;
    }
 /*Accesseurs**/
    public String getCommandWord()
    {
        return this.aCommandWord;
    }
    public String getSecondWord()
    {
        return this.aSecondWord;
    }
 //Fonctions
    public boolean hasSecondWord()
    {
        return this.aSecondWord!=null;
    }
    public boolean isUnknown()
    {
        return this.aCommandWord==null;
    }
} // Command
