/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views.GUI;

import Model.Individuals.CityTileset;
import Model.Individuals.Population;
import java.util.ArrayList;
import Views.View;


/**
 *
 * @author gabriel
 */
public class MainWindow extends javax.swing.JFrame implements View{

    private static MainWindow instance = null;
    private String appName = "NeigborhoodParkGA";
    
    ArrayList<CityView> actualPopulation;
    
    /*public static MainWindow getInstance () {
      if (instance == null) {
        instance = new MainWindow();
      }
      return instance;
    }*/
    
    public MainWindow() {
        setTitle(appName); //Add the title to frame
        setLayout(null); //Terminates default flow layout
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate program on close button
        setBounds(100, 200, 600, 600); //Sets the position of the frame
        
        actualPopulation = new ArrayList<>();
        
        initComponents();
        
        IndividualSelector.setEnabled(false);
    }

    public void updateView(){
        repaint();
        revalidate();
        System.out.print(CityView.PIXELSIZE);
    }
    
    private void drawCity(){
        IndividualShow_panel.removeAll();
        actualPopulation.get(IndividualSelector.getSelectedIndex()).updateView();
        IndividualShow_panel.add(actualPopulation.get(IndividualSelector.getSelectedIndex()));
        
    }
    
    public void setPopulationCT(Population<CityTileset> p){
                
        for(CityTileset i: p){
           IndividualSelector.addItem(Integer.toString(i.getId()));
           
           actualPopulation.add(new CityView(i));
        }
        
        IndividualSelector.setEnabled(true);
        drawCity();
    }
    
    public void showView() {
        setVisible(true);
    }
    
    public String getAppName(){
        return appName;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        IndividualShow_scroll = new javax.swing.JScrollPane();
        IndividualShow_panel = new javax.swing.JPanel();
        IndividualSelector = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        plusSize = new javax.swing.JButton();
        subSize = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(100, 100));

        IndividualShow_scroll.setMinimumSize(new java.awt.Dimension(100, 100));

        IndividualShow_panel.setAutoscrolls(true);
        IndividualShow_panel.setMinimumSize(new java.awt.Dimension(100, 100));
        IndividualShow_scroll.setViewportView(IndividualShow_panel);

        IndividualSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IndividualSelectorActionPerformed(evt);
            }
        });

        jLabel1.setText("SIze");

        plusSize.setText("+");
        plusSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusSizeActionPerformed(evt);
            }
        });

        subSize.setText("-");
        subSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subSizeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IndividualSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(plusSize)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(subSize)
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(IndividualShow_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IndividualSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(plusSize)
                    .addComponent(subSize))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IndividualShow_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IndividualSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IndividualSelectorActionPerformed
        // TODO add your handling code here:
        if(IndividualSelector.isEnabled())
            drawCity();
        updateView();
    }//GEN-LAST:event_IndividualSelectorActionPerformed

    private void plusSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusSizeActionPerformed
        if(CityView.PIXELSIZE < CityView.MAXPIXELSIZE){
            CityView.PIXELSIZE++;
        }
            
        drawCity();
        updateView();
    }//GEN-LAST:event_plusSizeActionPerformed

    private void subSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subSizeActionPerformed
        if(CityView.PIXELSIZE > CityView.MINPIXELSIZE)
            CityView.PIXELSIZE--;
        
        drawCity();
        updateView();
    }//GEN-LAST:event_subSizeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> IndividualSelector;
    private javax.swing.JPanel IndividualShow_panel;
    private javax.swing.JScrollPane IndividualShow_scroll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton plusSize;
    private javax.swing.JButton subSize;
    // End of variables declaration//GEN-END:variables
}
