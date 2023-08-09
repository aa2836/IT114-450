package Project.client.views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Project.client.Card;
import Project.client.ICardControls;

public class Menu extends JMenuBar{
    public Menu(ICardControls controls){
        JMenu roomsMenu = new JMenu("Rooms");
        JMenuItem roomsSearch = new JMenuItem("Search");
        roomsSearch.addActionListener((event) -> {
            controls.show(Card.ROOMS.name());
        });
        //aa2836 8/8/2023
        JMenuItem exportMenuItem = new JMenuItem("Export Chat History");
        exportMenuItem.addActionListener(e -> controls.exportChatHistory()); // Call exportChatHistory through ICardControls
        
        // Adding an ActionListener to the exportMenuItem using a lambda expression
        // When the menu item is clicked, it will execute the exportChatHistory()
        
        roomsMenu.add(roomsSearch);
        // Adding the exportMenuItem to the 'roomsMenu
        roomsMenu.add(exportMenuItem);
        // Adding the 'roomsMenu' to the current component
        this.add(roomsMenu);
    }
}