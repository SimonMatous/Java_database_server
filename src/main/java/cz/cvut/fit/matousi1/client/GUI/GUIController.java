package cz.cvut.fit.matousi1.client.GUI;

import cz.cvut.fit.matousi1.client.resources.*;

import java.util.Scanner;
import static java.lang.System.out;


enum Entity{
    GAME,
    LOCATION,
    SAVEFILE,
    SOFTWARE,
    STUDIO;
}


public class GUIController {

    Entity current;
    private int input;
    gameResource GameResource;
    locationResource LocationResource;
    savefileResource SavefileResource;
    studioResource StudioResource;
    softwareResource SoftwareResource;
    Scanner Scanner;

    public GUIController( gameResource gameResource, locationResource locationResource, savefileResource savefileResource, studioResource studioResource, softwareResource softwareResource) {
        GameResource = gameResource;
        LocationResource = locationResource;
        SavefileResource = savefileResource;
        StudioResource = studioResource;
        SoftwareResource = softwareResource;
        this.Scanner = new Scanner(System.in);
    }

    private void CreateEnums() {
        if (input == 1)
            current = Entity.GAME;
        if (input == 2)
            current = Entity.LOCATION;
        if (input == 3)
            current = Entity.SAVEFILE;
        if (input == 4)
            current = Entity.SOFTWARE;
        if (input == 5)
            current = Entity.STUDIO;
    }

    private void PrintMainMenuChoises(){
        //out.println("\t\tMain menu\n");
        out.println("Pick an entity to edit");
        out.println("1 - GAME"    );
        out.println("2 - LOCATION");
        out.println("3 - SAVEFILE");
        out.println("4 - SOFTWARE");
        out.println("5 - STUDIO"  );
        out.println("6 - END"     );
    }


}
