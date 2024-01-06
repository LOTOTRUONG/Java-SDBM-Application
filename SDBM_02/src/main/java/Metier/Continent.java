package Metier;

public class Continent {
    private Integer id;
    private String nomContinent;

    public Continent()
    {
        id=0;
        nomContinent = "";
    }
    public Continent(Integer id, String nomContinent)
    {
        this.id = id;
        this.nomContinent = nomContinent;
    }


    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNomContinent()
    {
        return nomContinent;
    }

    public void setNomContinent(String nomContinent)
    {
        this.nomContinent = nomContinent;
    }




    @Override
    public String toString()
    {
        return nomContinent;
    }
}
