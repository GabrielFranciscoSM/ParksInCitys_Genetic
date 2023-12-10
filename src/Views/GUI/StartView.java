/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views.GUI;

import Model.CityParameters;
import Model.ModelParameters;
import Model.ParksInCityGA;
import Views.View;
import javax.swing.JFrame;

/**
 *
 * @author gabriel
 */
public class StartView extends javax.swing.JFrame implements View{
    
    private String appName = "NeigborhoodParkGA";

    /**
     * Creates new form StartView
     */
    public StartView() {
        setTitle(getAppName()); //Add the title to frame
        setLayout(null); //Terminates default flow layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate program on close button
        setBounds(100, 200, 600, 600); //Sets the position of the frame

        showView();

        initComponents();
        
        ParameterTab.setTitleAt(0, "General");
        ParameterTab.setTitleAt(1, "City");
    }

    //Test
    public MainWindow generateMainWindow(){
        
        CityParameters cp = new CityParameters(
        cityParametersView1.getCitySizeValue(),
        cityParametersView1.getRoadDensity(),
        cityParametersView1.getBuildingDensity(),
        cityParametersView1.getParkSpreadness(),
        cityParametersView1.getParksPercentage());
        
        ModelParameters mp = new ModelParameters(
        modelParametersVIew1.getPopSizeValue(),
        modelParametersVIew1.getMoneyPonderationValue());
        
        ParksInCityGA model = new ParksInCityGA(cp,mp);
        model.run();
        
        MainWindow gui = new MainWindow();
        
        gui.setPopulationCT(model.getPopulation());
        
        MainWindow gui2 = new MainWindow();
        
        gui2.showView();
        
        return gui;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        ParameterTab = new javax.swing.JTabbedPane();
        cityParametersView1 = new Views.GUI.CityParametersView();
        modelParametersVIew1 = new Views.GUI.ModelParametersVIew();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Parks in the city");

        startButton.setText("START");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        ParameterTab.addTab("tab1", cityParametersView1);
        ParameterTab.addTab("tab2", modelParametersVIew1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addGap(161, 161, 161))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ParameterTab, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ParameterTab, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)))
        );

        ParameterTab.getAccessibleContext().setAccessibleName("general");
        ParameterTab.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
        generateMainWindow().showView();
    }//GEN-LAST:event_startButtonActionPerformed

  
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane ParameterTab;
    private Views.GUI.CityParametersView cityParametersView1;
    private javax.swing.JLabel jLabel1;
    private Views.GUI.ModelParametersVIew modelParametersVIew1;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateView() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void showView() {
        setVisible(true);
    }

    @Override
    public String getAppName() {
        return appName;
    }
}
