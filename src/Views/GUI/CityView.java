/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views.GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import Model.Individuals.CityTileset;
import javax.swing.JLabel;

/**
 *
 * @author gabriel
 */
public class CityView extends JButton{
    
    CityTileset ct;
    MainWindow mw;
    
    boolean isAum;
        
    CityView(CityTileset _ct, MainWindow _mw){
        
        ct = _ct;
        mw = _mw;
        
        setIcon(new ImageIcon(mw.createCityImage(ct)));
        Dimension size = getPreferredSize(); //Gets the size of the image
        setSize(size.width, size.height);
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    JFrame frame = new JFrame();
                    frame.setTitle("aum"); //Add the title to frame
                    frame.setLayout(null); //Terminates default flow layout

                    Container c = frame.getContentPane(); //Gets the content layer

                    JLabel bestIndividual = new JLabel(); //JLabel Creation
                    bestIndividual.setIcon(new ImageIcon(mw.createCityImage(ct,3)));
                    Dimension size = bestIndividual.getPreferredSize(); //Gets the size of the image
                    bestIndividual.setSize(size.width, size.height); //Sets the location of the image
                    frame.setBounds(400, 200, size.width, size.height); //Sets the position of the frame


                    c.add(bestIndividual); //Adds objects to the container
                    frame.setVisible(true); // Exhibit the frame

            }
        });
    }
}
