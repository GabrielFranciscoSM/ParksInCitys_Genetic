/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views.GUI;

import Model.Individuals.Tiles.TileType;
import Model.Individuals.Tiles.Tile;
import Model.Individuals.Tiles.BuildingTile;
import Model.Individuals.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;


import Views.View;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Container;
import java.awt.Dimension;

import Model.Individuals.CityTileset;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author gabriel
 */
public class MainWindow extends JFrame implements View {
    final static int PIXELSIZE = 1;
    private static MainWindow instance = null;
    private String appName = "NeigborhoodParkGA";
    
    private JFrame frame;
    private CityView bestIndividual;

    public static MainWindow getInstance () {
      if (instance == null) {
        instance = new MainWindow();
      }
      return instance;
    }
    
    private MainWindow() {
        frame = this; //JFrame Creation       
        frame.setTitle(appName); //Add the title to frame
        frame.setLayout(null); //Terminates default flow layout
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate program on close button
        frame.setBounds(100, 200, 600, 600); //Sets the position of the frame
        
    }

    public void updateView(){
        //createCityImage();
    }
    
    public BufferedImage createCityImage(CityTileset ct, int aumFactor){
        
        int ctSize = ct.getSize();
        
        BufferedImage ctImg = new BufferedImage(ctSize*PIXELSIZE*aumFactor,ctSize*PIXELSIZE*aumFactor, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g = (Graphics2D) ctImg.getGraphics();
        g.setStroke(new BasicStroke(PIXELSIZE*aumFactor));
        
        for(int i = 0; i < ctSize; ++i){
            for(int j = 0; j < ctSize; ++j){
                g.setColor(getTileColor(ct.getTile(i, j)));
                g.drawRect(i*PIXELSIZE*aumFactor, j*PIXELSIZE*aumFactor, 0, 0);
            }
        }
        
        return ctImg;
    }
    
    public BufferedImage createCityImage(CityTileset ct){
        
        int ctSize = ct.getSize();
        
        BufferedImage ctImg = new BufferedImage(ctSize*PIXELSIZE,ctSize*PIXELSIZE, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g = (Graphics2D) ctImg.getGraphics();
        g.setStroke(new BasicStroke(PIXELSIZE));
        
        for(int i = 0; i < ctSize; ++i){
            for(int j = 0; j < ctSize; ++j){
                g.setColor(getTileColor(ct.getTile(i, j)));
                g.drawRect(i*PIXELSIZE, j*PIXELSIZE, 0, 0);
            }
        }
        
        return ctImg;
    }
    
    public void setIcon(CityTileset ct){
        Container c = frame.getContentPane(); //Gets the content layer
        
        c.add(new CityView(ct,this)); //Adds objects to the container
        frame.setVisible(true); // Exhibit the frame
    }
    
    public void setPopulation(Population p){
        //JScrollPane Pop = new JScrollPane();
        JPanel jp = new JPanel();
        
        for(var i: p){
            jp.add(new CityView((CityTileset)i,this));
        }
        
        jp.setSize(jp.getPreferredSize());
        jp.setVisible(true);
        //Pop.add(jp);
        //Pop.setSize(400,400);
        
        JScrollPane scroll = new JScrollPane(
        jp, 
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
        );
        scroll.setSize(400,300);
        //Pop.setVisible(true);
        
        frame.getContentPane().add(scroll);
    }
    
    private Color getTileColor(Tile tl){
        Color clr = Color.white;
        if(tl.isVoid()){
            clr = Color.WHITE;
        }else if(tl.isPark()){
            clr = Color.GREEN;
        }else if(tl.isRoad()){
            clr = Color.BLACK;
        }else if(tl.isBuilding()){
            clr = new Color(200*tl.getValue(TileType.BUILDING)/BuildingTile.MAXCITIZEN,0,0);
        }
        
        return clr;
    }
    
    public void showView() {
        setVisible(true);
    }
    
    public String getAppName(){
        return appName;
    }
    
}
